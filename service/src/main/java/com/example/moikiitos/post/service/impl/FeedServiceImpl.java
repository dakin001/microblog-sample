package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.FeedCacheRepository;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.post.service.FeedService;
import com.example.moikiitos.shared.AppConfig;
import com.example.moikiitos.shared.PageQuery;
import com.example.moikiitos.shared.PageResult;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final PostRepository repository;
    private final FeedCacheRepository feedCacheRepository;
    private final UserQueryService userQueryService;
    private final AppConfig appConfig;

    @Override
//    @Cacheable(value = FEED_CACHE_KEY_PREFIX, key = "#reqDto.userId", condition = "#reqDto.skip == 0")
    public List<Post> queryUserFeed(FeedQueryDto reqDto) {
        // in order to simplify the logic, only cache first page feed.
        if (reqDto.getSkip() == 0) {
            var result = feedCacheRepository.findByUserId(reqDto.getUserId(), reqDto);
            if (result.isPresent()) {
                return result.get();
            }
        }

        List<Post> result = repository.findFeedByUserId(reqDto.getUserId(), reqDto);
        feedCacheRepository.save(reqDto.getUserId(), result);
        return result;
    }

    @Override
    public void addPostIntoFeed(Post post) {
        feedCacheRepository.addItemIfCacheExists(post.getUser().getId(), post);

        addIntoFollower(post);
    }

    @Override
    public void reGenerateFeed(Long userId) {
        List<Post> result = repository.findFeedByUserId(userId, new PageQuery());
        feedCacheRepository.save(userId, result);
    }

    private void addIntoFollower(Post post) {
        UserFollowQueryDto followQueryDto = new UserFollowQueryDto();
        followQueryDto.setLimit(appConfig.getBigVerifiedAccountFollowerSize());
        followQueryDto.setUserId(post.getUser().getId());

        var followers = userQueryService.listFollowers(followQueryDto);
        // we do not update big V timely. we can use scheduler job for big V's posts,
        if (isNotBigVerifiedAccount(followers)) {
            followers.getItems().forEach(follower -> feedCacheRepository.addItemIfCacheExists(follower.getId(), post));
        }
    }

    private boolean isNotBigVerifiedAccount(PageResult<User> pageResult) {
        return pageResult.getTotal() <= appConfig.getBigVerifiedAccountFollowerSize();
    }
}
