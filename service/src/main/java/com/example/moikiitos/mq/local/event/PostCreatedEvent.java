package com.example.moikiitos.mq.local.event;

import com.example.moikiitos.post.model.Post;

public class PostCreatedEvent extends BaseEvent<Post> {
    public PostCreatedEvent(Object source, Post message) {
        super(source, message);
    }
}