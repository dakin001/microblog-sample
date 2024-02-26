package com.example.moikiitos.mq.local.event;

import com.example.moikiitos.user.model.Follower;

public class FollowEvent extends BaseEvent<Follower> {
    public FollowEvent(Object source, Follower message) {
        super(source, message);
    }
}