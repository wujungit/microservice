package com.kanghe.component.rocketmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 11:15
 * @Description:
 */
@Data
public class CommonConfig {

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.groupName}")
    private String groupName;

}
