package com.example.moikiitos.domain.shared.mq;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.user.model.Follower;

public interface MqConsumeService {

    void receivePostCreatedMsg(Post obj);

    void receiveFollowMsg(Follower obj);

    void receiveUnFollowMsg(Follower obj);
}
