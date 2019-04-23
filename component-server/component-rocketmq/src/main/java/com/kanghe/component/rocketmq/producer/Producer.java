package com.kanghe.component.rocketmq.producer;

import com.kanghe.component.rocketmq.config.ProducerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 15:30
 * @Description:
 */
@Component
@Slf4j
public class Producer {

    @Autowired
    private ProducerConfig producerConfig;

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        //指定NameServer地址，多个地址以;隔开
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            log.info("execute defaultMQProducer(), producer start...");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

}
