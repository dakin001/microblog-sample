package com.example.moikiitos.domain.post.service.impl;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.service.UserFeedNotifyService;
import com.example.moikiitos.infrastructure.sse.SseEmitterServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Service
@RequiredArgsConstructor
public class UserFeedNotifyServiceImpl implements UserFeedNotifyService {
    private final SseEmitterServer sseEmitterServer;

    @Override
    public void notify(Long userId, Post obj) {
        // todo: this is simple code,  there may have a lot of work to do.
        //  find all user's connection; when deploy multiple instances each sever instance has independent topic channel.
        //  only receive notify if this instance has that connection id
        publish(String.valueOf(userId), obj);
    }

    @Override
    public ResponseBodyEmitter subscribe(Long userId) {
        // todo register userId's connections , same user may have multiple connections
        String connectionId = String.valueOf(userId);
        return sseEmitterServer.connect(connectionId);
    }

    private void publish(String connectionId, Post obj) {
        sseEmitterServer.sendMessage(connectionId, obj);
    }
}
