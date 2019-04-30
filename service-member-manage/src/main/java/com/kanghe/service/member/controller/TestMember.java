package com.kanghe.service.member.controller;

import com.kanghe.component.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: W_jun1
 * @Date: 2019/4/30 15:57
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class TestMember {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void setString() {
        redisUtil.set("T-STRING", "123");
    }

}
