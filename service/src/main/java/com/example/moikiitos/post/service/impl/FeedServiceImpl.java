package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.service.FeedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    @Override
    public List<Post> queryUserFeed(FeedQueryDto reqDto) {
        return null;
    }
}
