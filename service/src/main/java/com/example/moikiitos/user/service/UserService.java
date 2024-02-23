package com.example.moikiitos.user.service;

public interface UserService {

    void follow(String follower, String following);

    void unfollow(String follower, String following);
}
