package com.kanghe.business.core.manage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:48
 * @Description:
 */
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableSwagger2
@SpringBootApplication
public class CoreManageApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CoreManageApplication.class).run(args);
    }
}
