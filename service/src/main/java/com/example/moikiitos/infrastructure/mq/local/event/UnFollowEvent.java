package com.example.moikiitos.infrastructure.mq.local.event;

import com.example.moikiitos.domain.user.model.Follower;

public class UnFollowEvent extends BaseEvent<Follower> {
    public UnFollowEvent(Object source, Follower message) {
        super(source, message);
    }
}