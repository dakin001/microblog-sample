package com.example.moikiitos.mq.local;

import com.example.moikiitos.mq.MqProducerService;
import com.example.moikiitos.mq.local.event.FollowEvent;
import com.example.moikiitos.mq.local.event.PostCreatedEvent;
import com.example.moikiitos.mq.local.event.UnFollowEvent;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.user.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("local")
@Service
@RequiredArgsConstructor
public class MqProducerServiceImpl implements MqProducerService {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void sendPostCreatedMsg(Post obj) {
        applicationEventPublisher.publishEvent(new PostCreatedEvent(this, obj));
    }

    @Override
    public void sendFollowMsg(Follower obj) {
        applicationEventPublisher.publishEvent(new FollowEvent(this, obj));
    }

    @Override
    public void sendUnFollowMsg(Follower obj) {
        applicationEventPublisher.publishEvent(new UnFollowEvent(this, obj));
    }
}
