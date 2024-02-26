package com.example.moikiitos.infrastructure.mq.kafka;

import com.example.moikiitos.domain.shared.mq.MqProducerService;
import com.example.moikiitos.domain.post.model.Post;
import com.example.moikiitos.domain.user.model.Follower;

public class KafkaProducerServiceImpl implements MqProducerService {
    //    KafkaTemplate<Object, Object> template;
    @Override
    public void sendPostCreatedMsg(Post obj) {

    }

    @Override
    public void sendFollowMsg(Follower obj) {

    }

    @Override
    public void sendUnFollowMsg(Follower obj) {

    }
}
