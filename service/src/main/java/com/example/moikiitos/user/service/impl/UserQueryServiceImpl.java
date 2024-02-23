package com.example.moikiitos.user.service.impl;

import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;
import com.example.moikiitos.user.service.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Override
    public User findUserByNameOrEmail(UserQueryDto queryDto) {
        return null;
    }

    @Override
    public List<User> listFollowers(UserFollowQueryDto userFollowQueryDto) {
        return null;
    }

    @Override
    public List<User> listFollowing(UserFollowQueryDto userFollowQueryDto) {
        return null;
    }
}
