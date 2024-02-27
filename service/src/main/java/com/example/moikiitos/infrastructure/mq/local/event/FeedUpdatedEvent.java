package com.example.moikiitos.infrastructure.mq.local.event;

import com.example.moikiitos.domain.post.model.Post;

public class FeedUpdatedEvent extends BaseEvent<Post> {
    private Long userId;

    public FeedUpdatedEvent(Object source, Post message, Long userId) {
        super(source, message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}