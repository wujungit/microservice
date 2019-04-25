package com.kanghe.component.rocketmq.consumer;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ConsumerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:02
 * @Description:
 */
@Component
public class Consumer implements CommandLineRunner {

    private static final ConcurrentLinkedQueue<Message> MQ_QUEUE = new ConcurrentLinkedQueue<>();
    public static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Message>> MQ_CONTAINER = new ConcurrentHashMap<>();

    @Autowired
    private ConsumerConfig consumerConfig;

    @Override
    public void run(String... args) throws Exception {
        messageListener();
    }

    private void messageListener() {
        if (StringUtils.isBlank(consumerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(consumerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "namesrvAddr is blank");
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        try {
            consumer.subscribe("PushTopic", "");
            //在此监听中消费信息，并返回消费的状态信息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                // 会把不同的消息分别放置到不同的队列中
                for (Message msg : msgs) {
                    MQ_QUEUE.add(msg);
                    MQ_CONTAINER.put("PushTopic", MQ_QUEUE);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(e.getResponseCode(), e.getErrorMessage());
        }
    }

}
