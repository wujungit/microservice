package com.kanghe.component.rocketmq.service.impl;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import com.kanghe.component.rocketmq.service.IHandleMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

import static com.kanghe.component.rocketmq.consumer.Consumer.MQ_CONTAINER;

/**
 * @Author: W_jun1
 * @Date: 2019/4/25 11:02
 * @Description: 1、通过注册监听的方式来消费信息；2、通过拉取的方式来消费消息
 */
@Service
@Slf4j
public class HandleMQServiceImpl implements IHandleMQService {

    @Override
    public Message handle() {
        try {
            ConcurrentLinkedQueue<Message> queue = MQ_CONTAINER.get("PushTopic");
            return queue.peek();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RocketMQException(ResultEnum.MQ_EXECUTE_ERROR.getCode(), ResultEnum.MQ_EXECUTE_ERROR.getMsg());
        }
    }

}
