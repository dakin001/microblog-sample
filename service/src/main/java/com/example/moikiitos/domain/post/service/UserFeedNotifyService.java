package com.example.moikiitos.domain.post.service;

import com.example.moikiitos.domain.post.model.Post;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface UserFeedNotifyService {
    void notify(Long userId, Post obj);

    ResponseBodyEmitter subscribe(Long userId);
}
