package com.example.moikiitos.repository.redis;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.FeedCacheRepository;
import com.example.moikiitos.shared.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@RequiredArgsConstructor
public class FeedCacheRepositoryImpl implements FeedCacheRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${feedKeyPrefix:feed:}")
    private String feedKeyPrefix;
    @Value("${spring.cache.redis.time-to-live:600000}")
    private Long ttl = 600000L;

    @Override
    public void addItemIfCacheExists(Long userId, Post post) {
        String key = getKey(userId);
        redisTemplate.opsForZSet()
                .addIfAbsent(key, post, post.getId());
    }

    @Override
    public void save(Long userId, List<Post> posts) {
        if (posts.isEmpty()) {
            // todo: zSet not support empty cache, need to avoid Cache penetration
            return;
        }

        executedInTransaction(redisTemplate, redisTemplate -> {
            String key = getKey(userId);
            var set = new HashSet<ZSetOperations.TypedTuple<Object>>();
            for (var item : posts) {
                set.add(new DefaultTypedTuple<>(item, (double) item.getId()));
            }

            redisTemplate.opsForZSet()
                    .add(key, set);
            redisTemplate.expire(key, Duration.ofMillis(ttl));
        });
    }

    @Override
    public Optional<List<Post>> findByUserId(Long userId, PageQuery page) {
        var rangeData = redisTemplate.opsForZSet()
                .reverseRange(getKey(userId), page.getSkip() + 1, page.getSkip() + page.getLimit());

        List<Post> result = new ArrayList<>();
        if (rangeData != null) {
            rangeData.forEach(x -> result.add((Post) x));
        }
        // todo: zSet not support empty cache, need to avoid Cache penetration
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    private String getKey(Long userId) {
        return feedKeyPrefix + userId;
    }

    private void executedInTransaction(RedisTemplate<String, Object> redisTemplate,
                                       Consumer<RedisTemplate<String, Object>> consumer) {
        SessionCallback<?> callback = new SessionCallback<>() {
            @Override
            public Object execute(RedisOperations redisOperations) {
                redisOperations.multi();
                consumer.accept(redisTemplate);
                return redisOperations.exec();
            }
        };
        redisTemplate.execute(callback);
    }
}
