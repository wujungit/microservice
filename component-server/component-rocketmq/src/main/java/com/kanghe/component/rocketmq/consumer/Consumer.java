package com.kanghe.component.rocketmq.consumer;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ConsumerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:02
 * @Description: 1、通过注册监听的方式来消费信息（常用）；2、通过拉取的方式来消费消息；
 */
@Component
@Slf4j
public class Consumer implements CommandLineRunner {

    @Autowired
    private ConsumerConfig consumerConfig;

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner run...");
        messageListener();
    }

    /**
     * 1、通过注册监听的方式来消费信息；
     */
    private void messageListener() {
        if (StringUtils.isBlank(consumerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(consumerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "namesrvAddr is blank");
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
        consumer.setConsumerGroup(consumerConfig.getGroupName());
        try {
            // 订阅topic为"PushTopic",tag为"*"的消息
            consumer.subscribe("PushTopic", "*");
            // 程序第一次启动从消息队列头获取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            // 在此监听消费信息，并返回消费的状态信息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                // 会把不同的消息分别放置到不同的队列中（msgs中只收集同一个topic，同一个tag，并且key相同的message）
                for (Message msg : msgs) {
                    // 业务处理
                    System.out.println("接收到了消息：" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
            Thread.sleep(5000);
            //5秒后挂载消费端消费
            consumer.suspend();
        } catch (MQClientException | InterruptedException e) {
            e.printStackTrace();
            throw new RocketMQException(ResultEnum.MQ_EXECUTE_ERROR);
        }
    }

    /**
     * 2、通过拉取的方式来消费消息
     */
    private void messagePull() {
        if (StringUtils.isBlank(consumerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(consumerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "namesrvAddr is blank");
        }
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer();
        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
        consumer.setConsumerGroup(consumerConfig.getGroupName());
        try {
            consumer.start();
            Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("PushTopic");
            for (MessageQueue messageQueue : messageQueues) {
                System.out.println(messageQueue.getTopic());
            }
            //消息队列的监听，消息队列有改变，就会触发
            consumer.registerMessageQueueListener("", (topic, mqAll, mqDivided) -> {
                // 业务处理
            });
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(ResultEnum.MQ_EXECUTE_ERROR);
        }
    }

}
