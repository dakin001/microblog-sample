package com.example.moikiitos.domain.post.repository;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.shared.PageQuery;

import java.util.List;
import java.util.Optional;

public interface FeedCacheRepository {
    void addItemIfCacheExists(Long userId, Post post);

    void save(Long userId, List<Post> posts);

    Optional<List<Post>> findByUserId(Long userId, PageQuery page);
}
