package com.kanghe.service.order.controller;

import com.kanghe.component.rocketmq.annotation.MQListener;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
public class TestController {

    private static final String TOPIC = "PushTopic";
    private static final String TAG = "Push";

    @MQListener(topic = TOPIC, tag = TAG)
    @PostMapping("/pull")
    public String pullMsg(@RequestBody Message msg) {
        if (null != msg) {
            byte[] body = msg.getBody();
            if (null != body && body.length > 0) {
                System.out.println("接收到了消息：" + new String(body));
                return "SUCCEED";
            }
        }
        return "ERROR";
    }

}
