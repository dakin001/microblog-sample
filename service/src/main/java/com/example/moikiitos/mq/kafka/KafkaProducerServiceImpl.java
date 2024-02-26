package com.example.moikiitos.mq.kafka;

import com.example.moikiitos.mq.MqProducerService;
import com.example.moikiitos.post.model.Post;
import com.example.moikiitos.user.model.Follower;

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
