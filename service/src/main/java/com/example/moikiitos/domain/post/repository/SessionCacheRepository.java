package com.example.moikiitos.domain.post.repository;

import java.util.Set;

public interface SessionCacheRepository {
    void add(Long userId, String sessionId);

    Set<String> get(Long userId);

    void remove(Long userId);
}
