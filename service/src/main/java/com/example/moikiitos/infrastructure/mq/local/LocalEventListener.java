package com.example.moikiitos.infrastructure.mq.local;

import com.example.moikiitos.domain.shared.mq.MqConsumeService;
import com.example.moikiitos.infrastructure.mq.local.event.FeedUpdatedEvent;
import com.example.moikiitos.infrastructure.mq.local.event.FollowEvent;
import com.example.moikiitos.infrastructure.mq.local.event.PostCreatedEvent;
import com.example.moikiitos.infrastructure.mq.local.event.UnFollowEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Profile("local")
@Component
@RequiredArgsConstructor
public class LocalEventListener {
    private final MqConsumeService mqConsumeService;

    @EventListener
    public void handleContextStart(PostCreatedEvent event) {
        mqConsumeService.receivePostCreatedMsg(event.getMessage());
    }

    @EventListener
    public void handleContextStart(FollowEvent event) {
        mqConsumeService.receiveFollowMsg(event.getMessage());
    }

    @EventListener
    public void handleContextStart(UnFollowEvent event) {
        mqConsumeService.receiveUnFollowMsg(event.getMessage());
    }

    @EventListener
    public void handleContextStart(FeedUpdatedEvent event) {
        mqConsumeService.receiveFeedUpdatedMsg(event.getUserId(), event.getMessage());
    }
}
