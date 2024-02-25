package com.example.moikiitos.user.service.impl;

import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;
import com.example.moikiitos.user.repository.FollowerRepository;
import com.example.moikiitos.user.repository.UserQueryRepository;
import com.example.moikiitos.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserQueryRepository userQueryRepository;
    private final FollowerRepository followerRepository;

    @Override
    public User findUserByNameOrEmail(UserQueryDto queryDto) {
        return userQueryRepository.findByNameOrEmail(queryDto);
    }

    @Override
    public List<User> listFollowers(UserFollowQueryDto followQueryDto) {
        return followerRepository.findFollowers(followQueryDto);
    }

    @Override
    public List<User> listFollowing(UserFollowQueryDto followQueryDto) {
        return followerRepository.findFollowing(followQueryDto);
    }
}
