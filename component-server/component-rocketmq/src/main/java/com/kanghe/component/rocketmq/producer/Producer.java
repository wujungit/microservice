package com.kanghe.component.rocketmq.producer;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ProducerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 15:30
 * @Description:
 */
@Component
public class Producer {

    @Autowired
    private ProducerConfig producerConfig;

    private DefaultMQProducer producer;

    @Bean
    public DefaultMQProducer getProducer() {
        if (StringUtils.isBlank(producerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(producerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "nameServerAddr is blank");
        }
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
//        if (producerConfig.getMaxMessageSize() != null) {
//            producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
//        }
//        if (producerConfig.getSendMsgTimeout() != null) {
//            producer.setSendMsgTimeout(producerConfig.getSendMsgTimeout());
//        }
//        if (producerConfig.getRetryTimesWhenSendFailed() != null) {
//            producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
//        }

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(e.getResponseCode(), e.getErrorMessage());
        }

        return producer;
    }

    public String send(String topic, String tags, String body) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        Message message = new Message(topic, tags, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        StopWatch stop = new StopWatch();
        stop.start();
        SendResult result = producer.send(message);
        System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
        stop.stop();
        return "{\"MsgId\":\"" + result.getMsgId() + "\"}";
    }

}
