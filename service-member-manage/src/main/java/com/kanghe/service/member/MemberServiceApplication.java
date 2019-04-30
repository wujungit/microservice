package com.kanghe.service.member;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/11 11:56
 * @Description: 会员基础业务
 */
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.kanghe.service.member", "com.kanghe.component.redis"})
public class MemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApplication.class, args);
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("supportMethodsArguments", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
