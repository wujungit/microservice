package com.kanghe.component.rocketmq.producer;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ProducerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 15:30
 * @Description:
 */
@Configuration
public class ProducerConfigure {

    @Autowired
    private ProducerConfig producerConfig;

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        if (StringUtils.isBlank(producerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(producerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "nameServerAddr is blank");
        }
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        if (producerConfig.getMaxMessageSize() != null) {
            producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
        }
        if (producerConfig.getSendMsgTimeout() != null) {
            producer.setSendMsgTimeout(producerConfig.getSendMsgTimeout());
        }
        if (producerConfig.getRetryTimesWhenSendFailed() != null) {
            producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
        }

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(e.getResponseCode(), e.getErrorMessage());
        }

        return producer;
    }

}
