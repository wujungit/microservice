package com.kanghe.component.rocketmq.controller;

import com.kanghe.component.rocketmq.annotation.MQListener;
import com.kanghe.component.rocketmq.service.IHandleMQService;
import com.kanghe.component.rocketmq.service.ISendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
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

    private static final String TOPIC = "PushTopic";
    private static final String TAG = "Push";

    @Autowired
    private ISendMQService sendMQService;
    @Autowired
    private IHandleMQService handleMQService;

    @GetMapping("/push")
    public String pushMsg(@RequestParam String msg) {
        return sendMQService.send(TOPIC, TAG, msg);
    }

    @MQListener(topic = TOPIC, tag = TAG)
    @GetMapping("/pull")
    public void pullMsg() {
        Message msg = handleMQService.handle();
        System.out.println("接收到了消息：" + new String(msg.getBody()));
    }

}
