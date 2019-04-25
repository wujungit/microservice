package com.kanghe.component.rocketmq.service;

import org.apache.rocketmq.common.message.Message;

/**
 * @Author: W_jun1
 * @Date: 2019/4/25 10:28
 * @Description:
 */
@FunctionalInterface
public interface HandleMQService {

    /**
     * 处理MQ消息
     *
     * @param msg Message
     */
    public void handle(Message msg);

}
