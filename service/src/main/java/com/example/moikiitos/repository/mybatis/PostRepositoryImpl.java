package com.example.moikiitos.repository.mybatis;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.repository.mybatis.mapper.PostMapper;
import com.example.moikiitos.shared.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostMapper postMapper;

    @Override
    public void add(Post post) {
        postMapper.insert(post);
    }

    @Override
    public List<Post> queryFeed(Long userId, PageQuery page) {
        return null;
    }
}
