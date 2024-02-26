package com.example.moikiitos.domain.user.service;

import com.example.moikiitos.domain.user.model.User;

public interface UserService {

    void follow(User follower, Long followingId);

    void unfollow(User follower, Long followingId);
}
