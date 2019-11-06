package com.qf.component;

import com.qf.config.RockmqConfig;
import com.qf.service.SendEmail;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息的消费
 * @Configuration
 *    @Bean
 * @Component
 *    @Bean
 */
@Configuration
@Import(com.qf.config.RockmqConfig.class)
public class MessageConsumerCompoment {

    @Autowired
    private RockmqConfig rockmqConfig;


    /**
    @Bean(initMethod = "subsribe")
    public SendEmail sendEmail() throws MQClientException {
        SendEmail sendEmail = new SendEmail();


        // System.out.println(defaultMQPushConsumer  == defaultMQPushConsumer());
        sendEmail.setDefaultMQPushConsumer(defaultMQPushConsumer());

        System.out.println(sendEmail.getDefaultMQPushConsumer());

        return sendEmail;
    }
     */

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setConsumerGroup(rockmqConfig.getConsumerGroupName());
        defaultMQPushConsumer.setNamesrvAddr(rockmqConfig.getNameserverAddr());
        defaultMQPushConsumer.subscribe(rockmqConfig.getTopicName(), "tag1");

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("开始接受消息==========================");

                msgs.forEach(mt -> {
                    System.out.println(new String(mt.getBody()));
                });

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        defaultMQPushConsumer.start();
        return defaultMQPushConsumer;
    }
}
