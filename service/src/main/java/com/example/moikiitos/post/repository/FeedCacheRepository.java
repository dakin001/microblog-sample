package com.example.moikiitos.post.repository;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.shared.PageQuery;

import java.util.List;

public interface FeedCacheRepository {
    void addItemIfCacheExists(Long userId, Post post);

    void save(Long userId, List<Post> posts);

    List<Post> findByUserId(Long userId, PageQuery page);
}
