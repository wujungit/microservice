package com.kanghe.business.core.patient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:49
 * @Description:
 */
@EnableEurekaClient
@SpringBootApplication
public class CoreMemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CoreMemberApplication.class).run(args);
    }
}
