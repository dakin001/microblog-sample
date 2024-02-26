package com.example.moikiitos.mq.local;

import com.example.moikiitos.mq.MqConsumeService;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.post.service.FeedService;
import com.example.moikiitos.user.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqConsumeServiceImpl implements MqConsumeService {
    private final FeedService feedService;

    @Override
    public void receivePostCreatedMsg(Post obj) {
        feedService.addPostIntoFeed(obj);
    }

    @Override
    public void receiveFollowMsg(Follower obj) {
        feedService.reGenerateFeed(obj.getFollower().getId());
    }

    @Override
    public void receiveUnFollowMsg(Follower obj) {
        feedService.reGenerateFeed(obj.getFollower().getId());
    }
}
