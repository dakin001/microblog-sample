package com.example.moikiitos.user.service.impl;

import com.example.moikiitos.user.model.Follower;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.repository.FollowerRepository;
import com.example.moikiitos.user.repository.UserQueryRepository;
import com.example.moikiitos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final FollowerRepository followerRepository;
    private final UserQueryRepository userQueryRepository;

    @Override
    public void follow(User follower, String following) {
        Follower entity = getFollower(follower, following);
        if (entity == null) {
            return;
        }
        if (!followerRepository.isExists(entity)) {
            followerRepository.add(entity);
        }
    }

    @Override
    public void unfollow(User follower, String following) {
        Follower entity = getFollower(follower, following);
        if (entity != null) {
            followerRepository.remove(entity);
        }
    }

    private Follower getFollower(User follower, String following) {
        Follower entity = new Follower();
        entity.setFollower(follower);
        entity.setFollowing(userQueryRepository.findByName(following));
        if (entity.getFollower() == null || entity.getFollowing() == null) {
            return null;
        }
        return entity;
    }
}
