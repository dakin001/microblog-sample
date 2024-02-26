package com.example.moikiitos.domain.post.repository;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.shared.PageQuery;

import java.util.List;

public interface PostRepository {
    void add(Post post);

    List<Post> findFeedByUserId(Long userId, PageQuery page);
}
