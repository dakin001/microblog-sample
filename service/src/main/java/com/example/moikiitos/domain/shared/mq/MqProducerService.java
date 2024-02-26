package com.example.moikiitos.domain.shared.mq;

import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.user.model.Follower;

public interface MqProducerService {

    void sendPostCreatedMsg(Post obj);

    void sendFollowMsg(Follower obj);

    void sendUnFollowMsg(Follower obj);
}
