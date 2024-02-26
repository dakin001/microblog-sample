package com.example.moikiitos.infrastructure.mq.local;

import com.example.moikiitos.domain.shared.mq.MqConsumeService;
import com.example.moikiitos.infrastructure.mq.local.event.UnFollowEvent;
import com.example.moikiitos.infrastructure.mq.local.event.FollowEvent;
import com.example.moikiitos.infrastructure.mq.local.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
@RequiredArgsConstructor
public class LocalEventListener {
    private final MqConsumeService mqConsumeService;

    @EventListener
    public void handleContextStart(PostCreatedEvent cse) {
        mqConsumeService.receivePostCreatedMsg(cse.getMessage());
    }

    @EventListener
    public void handleContextStart(FollowEvent cse) {
        mqConsumeService.receiveFollowMsg(cse.getMessage());
    }

    @EventListener
    public void handleContextStart(UnFollowEvent cse) {
        mqConsumeService.receiveUnFollowMsg(cse.getMessage());
    }
}
