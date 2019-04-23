package com.kanghe.component.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: W_jun1
 * @Date: 2019/4/16 14:24
 * @Description: Apache RocketMQ
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class RocketMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQApplication.class, args);
    }

}
