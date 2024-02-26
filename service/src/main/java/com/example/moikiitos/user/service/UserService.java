package com.example.moikiitos.user.service;

import com.example.moikiitos.user.model.User;

public interface UserService {

    void follow(User follower, Long followingId);

    void unfollow(User follower, Long followingId);
}
