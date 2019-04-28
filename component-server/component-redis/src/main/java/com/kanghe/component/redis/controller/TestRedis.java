package com.kanghe.component.redis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: W_jun1
 * @Date: 2019/4/28 14:04
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() {
        String s = "字符串";
        stringRedisTemplate.opsForValue().set("T-STRING", s);
    }

}
