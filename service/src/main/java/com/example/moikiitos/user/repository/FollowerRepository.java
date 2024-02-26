package com.example.moikiitos.user.repository;

import com.example.moikiitos.shared.PageResult;
import com.example.moikiitos.user.model.Follower;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;

public interface FollowerRepository {
    void add(Follower follower);

    void remove(Follower follower);

    boolean isExists(Follower follower);

    PageResult<User> findFollowers(UserFollowQueryDto followQueryDto);

    PageResult<User> findFollowing(UserFollowQueryDto followQueryDto);
}
