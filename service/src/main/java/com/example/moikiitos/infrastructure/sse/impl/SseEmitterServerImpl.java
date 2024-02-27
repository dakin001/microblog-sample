package com.example.moikiitos.infrastructure.sse.impl;

import com.example.moikiitos.infrastructure.sse.SseEmitterServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
@Component
public class SseEmitterServerImpl implements SseEmitterServer {

    /**
     * local connections
     */
    private final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @Override
    public ResponseBodyEmitter connect(String connectId) {
        SseEmitter sseEmitter = new SseEmitter(Duration.ofMinutes(60L).toMillis());
        sseEmitter.onCompletion(completionCallBack(connectId));
        sseEmitter.onError(errorCallBack(connectId));
        sseEmitter.onTimeout(timeoutCallBack(connectId));
        sseEmitterMap.put(connectId, sseEmitter);

        log.debug("create sse connection，current：{}", connectId);
        return sseEmitter;
    }

    /**
     * sendMessageToHttpConnect
     */
    @Override
    public void sendMessage(String connectId, Object message) {
        SseEmitter sseEmitter = sseEmitterMap.get(connectId);
        if (sseEmitter == null) {
            return;
        }
        try {
            sseEmitter.send(message);
        } catch (IOException e) {
            log.error("connectId{}] exception:{}", connectId, e.getMessage());
            removeUser(connectId);
        }
    }

    public void removeUser(String connectId) {
        sseEmitterMap.remove(connectId);

        log.debug("remove connection from cache：{}", connectId);
    }

    private Runnable completionCallBack(String connectId) {
        return () -> {
            log.debug("connection completed：{}", connectId);
            removeUser(connectId);
        };
    }

    private Runnable timeoutCallBack(String connectId) {
        return () -> {
            log.debug("connection timeout：{}", connectId);
            removeUser(connectId);
        };
    }

    private Consumer<Throwable> errorCallBack(String connectId) {
        return throwable -> {
            log.debug("connection error：{}", connectId);
            removeUser(connectId);
        };
    }
}