package com.kanghe.component.rocketmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 11:15
 * @Description:
 */
@Data
@Configuration
public class ProducerConfig {

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String groupName;

//    /**
//     * 消息最大大小，默认4M
//     */
//    @Value("${rocketmq.producer.maxMessageSize}")
//    private Integer maxMessageSize;
//
//    /**
//     * 消息发送超时时间，默认3秒
//     */
//    @Value("${rocketmq.producer.sendMsgTimeout}")
//    private Integer sendMsgTimeout;
//
//    /**
//     * 消息发送失败重试次数，默认2次
//     */
//    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
//    private Integer retryTimesWhenSendFailed;

}
