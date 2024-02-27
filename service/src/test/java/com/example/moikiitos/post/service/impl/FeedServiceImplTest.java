package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.domain.post.model.FeedQueryDto;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.repository.FeedCacheRepository;
import com.example.moikiitos.domain.post.repository.PostRepository;
import com.example.moikiitos.domain.post.service.impl.FeedServiceImpl;
import com.example.moikiitos.domain.shared.AppConfig;
import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.service.UserQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedServiceImplTest {

    @Mock
    PostRepository postRepository;
    @Mock
    FeedCacheRepository feedCacheRepository;
    @Mock
    UserQueryService userQueryService;
    @Mock
    MqProducerService mqProducerService;

    FeedServiceImpl service;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        appConfig.setBigVerifiedAccountFollowerSize(10);
        service = new FeedServiceImpl(postRepository, feedCacheRepository, userQueryService, appConfig, mqProducerService);
    }

    @Test
    void queryUserFeed_noCache_saveCache() {
        // CASE
        FeedQueryDto queryDto = new FeedQueryDto();
        queryDto.setUserId(1L);
        when(feedCacheRepository.findByUserId(queryDto.getUserId(), queryDto)).thenReturn(Optional.empty());

        // WHEN
        service.queryUserFeed(queryDto);

        // THEN
        verify(postRepository, Mockito.times(1)).findFeedByUserId(1L, queryDto);
        verify(feedCacheRepository, Mockito.times(1)).save(any(), any());
    }

    @Test
    void queryUserFeed_emptyCache_returnCache() {
        // CASE
        FeedQueryDto queryDto = new FeedQueryDto();
        queryDto.setUserId(1L);
        when(feedCacheRepository.findByUserId(queryDto.getUserId(), queryDto)).thenReturn(Optional.of(new ArrayList<>()));

        // WHEN
        service.queryUserFeed(queryDto);

        // THEN
        verify(postRepository, never()).findFeedByUserId(any(), any(FeedQueryDto.class));
    }

    @Test
    void addPostIntoFeed_smallFollowerSize_updatePosterAndAllFollower() {
        // CASE
        User user = new User();
        user.setId(10000L);

        List<User> followers = getUsers(2);

        Post post = new Post();
        post.setUser(user);

        when(userQueryService.listFollowers(any(UserFollowQueryDto.class)))
                .thenReturn(new PageResult<>(followers));

        // WHEN
        service.addPostIntoFeed(post);

        // THEN
        verify(feedCacheRepository, times(1)).addItemIfCacheExists(user.getId(), post);
        verify(feedCacheRepository, times(1)).addItemIfCacheExists(2L, post);
        verify(feedCacheRepository, times(3)).addItemIfCacheExists(any(), any());
    }

    @Test
    void addPostIntoFeed_bigFollowerSize_updatePosterOnly() {
        // CASE
        User user = new User();
        user.setId(10000L);

        List<User> followers = getUsers(21);

        Post post = new Post();
        post.setUser(user);

        when(userQueryService.listFollowers(any(UserFollowQueryDto.class)))
                .thenReturn(new PageResult<>(followers));

        // WHEN
        service.addPostIntoFeed(post);

        // THEN
        verify(feedCacheRepository, times(1)).addItemIfCacheExists(any(), any());
    }

    private List<User> getUsers(int size) {
        List<User> followers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            User user2 = new User();
            user2.setId((long) i);
            followers.add(user2);
        }
        return followers;
    }
}