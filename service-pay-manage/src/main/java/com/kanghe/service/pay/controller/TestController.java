package com.kanghe.service.pay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
public class TestController {

    @GetMapping("/push")
    public String pushMsg(@RequestParam String msg) {
        return "SUCCEED";
    }

}
