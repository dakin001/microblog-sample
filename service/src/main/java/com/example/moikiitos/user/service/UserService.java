package com.example.moikiitos.user.service;

import com.example.moikiitos.user.model.User;

public interface UserService {

    void follow(User follower, String following);

    void unfollow(User follower, String following);
}
