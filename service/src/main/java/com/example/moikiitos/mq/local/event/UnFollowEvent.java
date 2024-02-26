package com.example.moikiitos.mq.local.event;

import com.example.moikiitos.user.model.Follower;

public class UnFollowEvent extends BaseEvent<Follower> {
    public UnFollowEvent(Object source, Follower message) {
        super(source, message);
    }
}