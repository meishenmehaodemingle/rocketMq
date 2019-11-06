package com.qf.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RockmqConfig.class)
public class SendMailComponent {

    @Autowired
    private RockmqConfig rockmqConfig;

    // 消息的生产者
    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(); //消息生成者
        // 设置NameServer
        defaultMQProducer.setNamesrvAddr(rockmqConfig.getNameserverAddr());

        //设置生产者组
        defaultMQProducer.setProducerGroup(rockmqConfig.getProducerGroupName());

        try {
            defaultMQProducer.start();  //开启生产者组
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        return defaultMQProducer;
    }
}
