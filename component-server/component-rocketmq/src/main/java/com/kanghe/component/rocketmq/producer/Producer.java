package com.kanghe.component.rocketmq.producer;

import com.kanghe.component.rocketmq.config.ProducerConfig;
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
            System.out.println("-------->:producer启动了");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

}
