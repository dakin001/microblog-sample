package com.example.moikiitos.domain.user.service.impl;

import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.repository.FollowerRepository;
import com.example.moikiitos.domain.user.repository.UserQueryRepository;
import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.model.UserQueryDto;
import com.example.moikiitos.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public PageResult<User> listFollowers(UserFollowQueryDto followQueryDto) {
        return followerRepository.findFollowers(followQueryDto);
    }

    @Override
    public PageResult<User> listFollowing(UserFollowQueryDto followQueryDto) {
        return followerRepository.findFollowing(followQueryDto);
    }
}
