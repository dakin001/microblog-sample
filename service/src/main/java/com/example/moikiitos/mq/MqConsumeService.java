package com.example.moikiitos.mq;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.user.model.Follower;

public interface MqConsumeService {

    void receivePostCreatedMsg(Post obj);

    void receiveFollowMsg(Follower obj);

    void receiveUnFollowMsg(Follower obj);
}
