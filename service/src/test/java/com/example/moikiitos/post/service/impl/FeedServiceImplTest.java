package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.repository.UserQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedServiceImplTest {

    @Mock
    PostRepository postRepository;
    @Mock
    UserQueryRepository userQueryRepository;

    FeedServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new FeedServiceImpl(postRepository, userQueryRepository);
    }

    @Test
    void queryUserFeed() {
        // CASE
        FeedQueryDto queryDto = new FeedQueryDto();
        queryDto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        user.setName("user1");
        when(userQueryRepository.findById(1L)).thenReturn(user);

        // WHEN
        service.queryUserFeed(queryDto);

        // THEN
        verify(postRepository, Mockito.times(1)).findFeedByUserId(1L, queryDto);
    }
}