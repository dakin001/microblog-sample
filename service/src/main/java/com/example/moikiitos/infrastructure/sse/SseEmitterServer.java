package com.example.moikiitos.infrastructure.sse;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface SseEmitterServer {
    ResponseBodyEmitter connect(String connectId);

    void sendMessage(String connectId, Object message);
}
