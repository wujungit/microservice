package com.kanghe.component.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.kanghe.component.rocketmq.service.IHandleMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/4/25 11:02
 * @Description: 1、通过注册监听的方式来消费信息；2、通过拉取的方式来消费消息
 */
@Service
@Slf4j
public class HandleMQServiceImpl implements IHandleMQService {

    @Autowired
    private DefaultMQPushConsumer consumer;

    @Override
    public Message handle() {
        List<Message> list = new ArrayList<>();
        //在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, consumeConcurrentlyContext) -> {
            list.addAll(messages);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        log.info("list: {}", JSON.toJSONString(list));
        return list.isEmpty() ? new Message() : list.get(0);
    }

}
