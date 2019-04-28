package com.kanghe.component.rocketmq.controller;

import com.kanghe.component.rocketmq.annotation.MQListener;
import com.kanghe.component.rocketmq.service.ISendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/mq")
public class TestController {

    private static final String TOPIC = "PushTopic";
    private static final String TAG = "Push";

    @Autowired
    private ISendMQService sendMQService;

    @GetMapping("/push")
    public String pushMsg(@RequestParam String msg) {
        return sendMQService.send(TOPIC, TAG, msg);
    }

    @MQListener(topic = TOPIC, tag = TAG)
    @PostMapping("/pull")
    public String pullMsg(@RequestBody Message msg) {
        if (null != msg) {
            byte[] body = msg.getBody();
            if (null != body && body.length > 0) {
                log.info("receive message: {}", new String(body));
                return "SUCCEED";
            }
        }
        return "ERROR";
    }

}
