package com.kanghe.component.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 16:17
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping("/pull")
    public String pullMsg(@RequestBody Object o) {
        if (null != o) {
            return "SUCCEED";
        }
        return "ERROR";
    }

}
