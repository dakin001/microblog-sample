package com.example.moikiitos.post.service.impl;

import com.example.moikiitos.post.model.FeedQueryDto;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.repository.PostRepository;
import com.example.moikiitos.post.service.FeedService;
import com.example.moikiitos.user.model.User;
import com.example.moikiitos.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.moikiitos.shared.ApplicationConst.FEED_CACHE_KEY_PREFIX;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final PostRepository repository;
    private final UserQueryRepository userQueryRepository;

    @Override
    @Cacheable(value = FEED_CACHE_KEY_PREFIX, key = "#reqDto.name", condition = "#reqDto.skip == 0")
    public List<Post> queryUserFeed(FeedQueryDto reqDto) {
        User user = userQueryRepository.findByName(reqDto.getName());
        if (user == null) {
            return new ArrayList<>();
        }

        return repository.findFeedByUserId(user.getId(), reqDto);
    }
}
