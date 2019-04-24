package com.kanghe.component.rocketmq.controller;

import com.kanghe.component.rocketmq.annotation.MQListener;
import com.kanghe.component.rocketmq.service.ISendMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private ISendMQService sendMQService;

    @GetMapping("/push")
    public String pushMsg(@RequestParam String msg) {
        return sendMQService.send("PushTopic", "Push", msg);
    }

    @MQListener(topic = "PushTopic", tag = "Push")
    @GetMapping("/pull")
    public String pullMsg() {
        log.info("execute pullMsg()");
        return "SUCCEED";
    }

}
