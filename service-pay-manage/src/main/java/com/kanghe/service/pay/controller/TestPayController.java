package com.kanghe.service.pay.controller;

import com.kanghe.component.rocketmq.service.ISendMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
@RequestMapping(value = "/pay")
@Slf4j
public class TestPayController {

    private static final String TOPIC = "PushTopic";
    private static final String TAG = "Push";

    @Autowired
    private ISendMQService sendMQService;

    @GetMapping(value =  "/push", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String pushMsg(@RequestParam String msg) {
        return sendMQService.send(TOPIC, TAG, msg);
    }

}
