package com.kanghe.service.member.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;


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

    @Test
    public void copyFile() {
        Resource resource = new ClassPathResource("mapper");
        log.info(resource.getFilename());
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File[] files = resource.getFile().listFiles();
            if (null != files && files.length > 0) {
                for (File file : files) {
                    br = new BufferedReader(new FileReader(file));
                    String fileName = "C:/Users/W_jun1/Desktop/download/" + file.getName();
                    bw = new BufferedWriter(new FileWriter(fileName));
                    String temp;
                    while ((temp = br.readLine()) != null) {
                        bw.write(temp + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br && null != bw) {
                try {
                    br.close();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
