package com.example.moikiitos.repository.redis;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.FeedCacheRepository;
import com.example.moikiitos.shared.PageQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedCacheRepositoryImpl implements FeedCacheRepository {
    @Override
    public void addItemIfCacheExists(Long userId, Post post) {

    }

    @Override
    public void save(Long userId, List<Post> posts) {

    }

    @Override
    public List<Post> findByUserId(Long userId, PageQuery page) {
        return null;
    }
}
