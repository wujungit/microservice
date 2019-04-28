package com.kanghe.component.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private static RedisTemplate redisTemplate;

    public static void main(String[] args) {
        String s = "字符串";
        stringRedisTemplate.opsForValue().set("T-STRING", s);
    }

    @PostMapping("/pull")
    public String pullMsg(@RequestBody Object o) {
        if (null != o) {
            return "SUCCEED";
        }
        return "ERROR";
    }

}
