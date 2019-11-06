package com.qf.controller;

import com.qf.config.RockmqConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
@Import(RockmqConfig.class)
public class UserRegsiterController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private RockmqConfig rockmqConfig;

    @RequestMapping
    public Object register(String email, String name) throws InterruptedException,
            RemotingException, MQClientException, MQBrokerException {

        Message message = new Message();
        message.setBody(("恭喜你，" + name + ", 注册成功.").getBytes());
        message.setTopic(rockmqConfig.getTopicName());
        message.setTags(rockmqConfig.getTopicTag());

        defaultMQProducer.send(message);  //消息的发布

        System.out.println("controller 中调用发送消息结束");

        return "success";
    }
}
