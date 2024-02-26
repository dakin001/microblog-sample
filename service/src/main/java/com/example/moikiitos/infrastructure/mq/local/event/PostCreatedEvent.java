package com.example.moikiitos.infrastructure.mq.local.event;

import com.example.moikiitos.domain.post.model.Post;

public class PostCreatedEvent extends BaseEvent<Post> {
    public PostCreatedEvent(Object source, Post message) {
        super(source, message);
    }
}