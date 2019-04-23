package com.kanghe.component.rocketmq.service.impl;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import com.kanghe.component.rocketmq.service.ISendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 16:41
 * @Description:
 */
@Service
@Slf4j
public class SendMQServiceImpl implements ISendMQService {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Override
    public String send(String topic, String tags, String body) {
        try {
            log.info("execute send(): topic={},tags={},body={}", topic, tags, body);
            Message message = new Message(topic, tags, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            defaultMQProducer.send(message);
            return "SUCCEED";
        } catch (UnsupportedEncodingException | MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            throw new RocketMQException(ResultEnum.MQ_EXECUTE_ERROR.getCode(), ResultEnum.MQ_EXECUTE_ERROR.getMsg());
        }
    }

}
