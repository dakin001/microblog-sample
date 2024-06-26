package com.example.moikiitos.infrastructure.mq.impl;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.post.service.FeedService;
import com.example.moikiitos.domain.post.service.UserFeedNotifyService;
import com.example.moikiitos.domain.shared.mq.MqConsumeService;
import com.example.moikiitos.domain.user.model.Follower;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqConsumeServiceImpl implements MqConsumeService {
    private final FeedService feedService;
    private final UserFeedNotifyService notifyService;

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

    @Override
    public void receiveFeedUpdatedMsg(Long userId, Post obj) {
        notifyService.notify(userId, obj);
    }
}
