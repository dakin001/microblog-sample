package com.example.moikiitos.mq;

import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.user.model.Follower;

public interface MqProducerService {

    void sendPostCreatedMsg(Post obj);

    void sendFollowMsg(Follower obj);

    void sendUnFollowMsg(Follower obj);
}
