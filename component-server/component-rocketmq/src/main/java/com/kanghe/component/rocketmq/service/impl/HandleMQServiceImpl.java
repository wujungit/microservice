package com.kanghe.component.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ConsumerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import com.kanghe.component.rocketmq.service.IHandleMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
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
    private ConsumerConfig consumerConfig;

    @Override
    public Message handle() {
        try {
            if (StringUtils.isBlank(consumerConfig.getGroupName())) {
                throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
            }
            if (StringUtils.isBlank(consumerConfig.getNamesrvAddr())) {
                throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "namesrvAddr is blank");
            }
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
            consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.subscribe("PushTopic", "");

            //在此监听中消费信息，并返回消费的状态信息
            List<Message> list = new ArrayList<>();
            consumer.registerMessageListener((MessageListenerConcurrently) (messages, consumeConcurrentlyContext) -> {
                list.addAll(messages);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            log.info("list: {}", JSON.toJSONString(list));
            return list.isEmpty() ? new Message() : list.get(0);
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(ResultEnum.MQ_EXECUTE_ERROR.getCode(), ResultEnum.MQ_EXECUTE_ERROR.getMsg());
        }
    }

}
