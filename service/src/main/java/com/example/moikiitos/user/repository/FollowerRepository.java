package com.example.moikiitos.user.repository;

import com.example.moikiitos.user.model.Follower;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.model.UserFollowQueryDto;

import java.util.List;

public interface FollowerRepository {
    void add(Follower follower);

    void remove(Follower follower);

    boolean isExists(Follower follower);

    List<User> listFollowers(UserFollowQueryDto followQueryDto);

    List<User> listFollowing(UserFollowQueryDto followQueryDto);
}
