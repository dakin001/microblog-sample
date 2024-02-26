package com.example.moikiitos.user.service;

import com.example.moikiitos.shared.PageResult;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;
import com.example.moikiitos.user.model.UserQueryDto;

public interface UserQueryService {

    User findUserByNameOrEmail(UserQueryDto queryDto);

    PageResult<User> listFollowers(UserFollowQueryDto userFollowQueryDto);

    PageResult<User> listFollowing(UserFollowQueryDto userFollowQueryDto);
}
