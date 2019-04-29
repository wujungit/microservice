package com.kanghe.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: W_jun1
 * @Date: 2019/4/16 14:24
 * @Description: service-order-manage
 */
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.kanghe.component.rocketmq"})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
