package com.example.moikiitos.domain.user.service;

import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;
import com.example.moikiitos.domain.user.model.UserQueryDto;

public interface UserQueryService {

    User findUserByNameOrEmail(UserQueryDto queryDto);

    PageResult<User> listFollowers(UserFollowQueryDto userFollowQueryDto);

    PageResult<User> listFollowing(UserFollowQueryDto userFollowQueryDto);
}
