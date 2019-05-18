package com.kanghe.service.member.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;


/**
 * @Author: W_jun1
 * @Date: 2019/4/30 15:57
 * @Description:
 */
//@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class TestMember {

//    @Autowired
//    private RedisUtil redisUtil;

    @Test
    public void setString() {
//        RedisUtil.set("T-STRING", "123");
        try {
            Resource resource = new ClassPathResource("/mapper");
            File file = resource.getFile();
            String filename = resource.getFilename();
            System.out.println(JSON.toJSONString(file.list()));
            System.out.println(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
