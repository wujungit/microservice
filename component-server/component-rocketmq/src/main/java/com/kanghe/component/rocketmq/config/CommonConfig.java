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
public class CommonConfig {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.groupName}")
    private String groupName;

}
