package com.example.moikiitos.infrastructure.repository.redis;

import com.example.moikiitos.domain.post.repository.SessionCacheRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SessionCacheRepositoryImpl implements SessionCacheRepository {
    @Override
    public void add(Long userId, String sessionId) {
        //todo
    }

    @Override
    public Set<String> get(Long userId) {
        //todo
        return new HashSet<>();
    }

    @Override
    public void remove(Long userId) {
        //todo
    }
}
