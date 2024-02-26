package com.example.moikiitos.user.service.impl;

import com.example.moikiitos.user.model.Follower;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.repository.FollowerRepository;
import com.example.moikiitos.user.repository.UserQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    FollowerRepository followerRepository;
    @Mock
    UserQueryRepository userQueryRepository;

    UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(followerRepository, userQueryRepository);
    }

    @Test
    void follow_notExists_unfollowToFollow() {
        // CASE
        User user1 = new User();
        user1.setId(1L);
        user1.setName("user1");
        User user2 = new User();
        user2.setId(2L);
        user2.setName("user2");
        when(userQueryRepository.findById(2L)).thenReturn(user2);

        // WHEN
        service.follow(user1, 2L);

        // THEN
        verify(followerRepository, Mockito.times(1)).add(any(Follower.class));
    }

    @Test
    void follow_exists_noChange() {
        // CASE
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user1.setName("user2");
        when(userQueryRepository.findById(2L)).thenReturn(user2);
        when(followerRepository.isExists(any(Follower.class))).thenReturn(true);

        // WHEN
        service.follow(user1, 2L);

        // THEN
        verify(followerRepository, never()).add(any(Follower.class));
    }

    @Test
    void unfollow_doNotCareExits_remove() {
        // CASE
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user1.setName("user2");
        when(userQueryRepository.findById(2L)).thenReturn(user2);

        // WHEN
        service.unfollow(user1, 2L);

        // THEN
        verify(followerRepository, Mockito.times(1)).remove(any(Follower.class));
    }
}