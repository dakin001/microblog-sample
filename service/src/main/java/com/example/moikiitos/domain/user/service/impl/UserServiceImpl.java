package com.example.moikiitos.domain.user.service.impl;

import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.user.model.Follower;
import com.example.moikiitos.domain.user.model.User;
import com.example.moikiitos.domain.user.repository.FollowerRepository;
import com.example.moikiitos.domain.user.repository.UserQueryRepository;
import com.example.moikiitos.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final FollowerRepository followerRepository;
    private final UserQueryRepository userQueryRepository;
    private final MqProducerService mqService;

    @Override
    public void follow(User follower, Long followingId) {
        Follower entity = getFollower(follower, followingId);
        if (entity == null) {
            return;
        }
        if (!followerRepository.isExists(entity)) {
            followerRepository.add(entity);
            mqService.sendFollowMsg(entity);
        }
    }

    @Override
    public void unfollow(User follower, Long followingId) {
        Follower entity = getFollower(follower, followingId);
        if (entity != null) {
            followerRepository.remove(entity);
            mqService.sendUnFollowMsg(entity);
        }
    }

    private Follower getFollower(User follower, Long followingId) {
        Follower entity = new Follower();
        entity.setFollower(follower);
        entity.setFollowing(userQueryRepository.findById(followingId));
        if (entity.getFollower() == null || entity.getFollowing() == null) {
            return null;
        }
        return entity;
    }
}
