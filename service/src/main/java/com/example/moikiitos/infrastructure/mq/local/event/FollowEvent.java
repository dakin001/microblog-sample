package com.example.moikiitos.infrastructure.mq.local.event;

import com.example.moikiitos.domain.user.model.Follower;

public class FollowEvent extends BaseEvent<Follower> {
    public FollowEvent(Object source, Follower message) {
        super(source, message);
    }
}