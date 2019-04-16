package com.kanghe.component.rocketmq.consumer;

import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.rocketmq.config.ConsumerConfig;
import com.kanghe.component.rocketmq.exception.RocketMQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:02
 * @Description:
 */
@Component
public class Consumer {

    @Autowired
    private ConsumerConfig consumerConfig;

    @Bean
    public DefaultMQPushConsumer getConsumer() {
        if (StringUtils.isBlank(consumerConfig.getGroupName())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "groupName is blank");
        }
        if (StringUtils.isBlank(consumerConfig.getNamesrvAddr())) {
            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "namesrvAddr is blank");
        }
//        if (StringUtils.isBlank(consumerConfig.getTopics())) {
//            throw new RocketMQException(ResultEnum.INVALID_PARAM.getCode(), "topics is blank");
//        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());
        consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());
//        consumer.setConsumeThreadMin(consumerConfig.getConsumeThreadMin());
//        consumer.setConsumeThreadMax(consumerConfig.getConsumeThreadMax());
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.setConsumeMessageBatchMaxSize(consumerConfig.getConsumeMessageBatchMaxSize());

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            throw new RocketMQException(e.getResponseCode(), e.getErrorMessage());
        }

        return consumer;
    }

}
