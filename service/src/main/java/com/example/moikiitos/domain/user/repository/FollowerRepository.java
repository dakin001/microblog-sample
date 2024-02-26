package com.example.moikiitos.domain.user.repository;

import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.shared.PageResult;
import com.example.moikiitos.domain.user.model.Follower;
import com.example.moikiitos.domain.user.model.UserFollowQueryDto;

public interface FollowerRepository {
    void add(Follower follower);

    void remove(Follower follower);

    boolean isExists(Follower follower);

    PageResult<User> findFollowers(UserFollowQueryDto followQueryDto);

    PageResult<User> findFollowing(UserFollowQueryDto followQueryDto);
}
