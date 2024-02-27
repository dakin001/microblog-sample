package com.example.moikiitos.domain.post.service.impl;

import com.example.moikiitos.domain.post.model.FeedQueryDto;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.repository.FeedCacheRepository;
import com.example.moikiitos.domain.post.repository.PostRepository;
import com.example.moikiitos.domain.post.service.FeedService;
import com.example.moikiitos.domain.shared.AppConfig;
import com.example.moikiitos.domain.shared.PageQuery;
import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.service.UserQueryService;
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
    private final MqProducerService mqProducerService;

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
        addOneIntoFeed(post.getUser().getId(), post);

        addIntoFollower(post);
    }

    @Override
    public void reGenerateFeed(Long userId) {
        List<Post> result = repository.findFeedByUserId(userId, new PageQuery());
        feedCacheRepository.save(userId, result);
    }

    private void addOneIntoFeed(Long userId, Post post) {
        feedCacheRepository.addItemIfCacheExists(userId, post);

        mqProducerService.sendFeedUpdatedMsg(userId, post);
    }

    private void addIntoFollower(Post post) {
        UserFollowQueryDto followQueryDto = new UserFollowQueryDto();
        followQueryDto.setLimit(appConfig.getBigVerifiedAccountFollowerSize());
        followQueryDto.setUserId(post.getUser().getId());

        var followers = userQueryService.listFollowers(followQueryDto);
        // we do not update big V timely. we can use scheduler job for big V's posts,
        if (isNotBigVerifiedAccount(followers)) {
            followers.getItems().forEach(follower -> addOneIntoFeed(follower.getId(), post));
        }
    }

    private boolean isNotBigVerifiedAccount(PageResult<User> pageResult) {
        return pageResult.getTotal() <= appConfig.getBigVerifiedAccountFollowerSize();
    }
}
