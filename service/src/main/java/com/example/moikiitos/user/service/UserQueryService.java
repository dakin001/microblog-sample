package com.example.moikiitos.user.service;

import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;

import java.util.List;

public interface UserQueryService {

    User findUserByNameOrEmail(UserQueryDto queryDto);

    List<User> listFollowers(UserFollowQueryDto userFollowQueryDto);

    List<User> listFollowing(UserFollowQueryDto userFollowQueryDto);
}
