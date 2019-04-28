package com.kanghe.component.redis.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: W_jun1
 * @Date: 2019/4/28 14:04
 * @Description: 1、StringRedisTemplate：存取字符串类型的数据；
 * 2、redisTemplate：数据是复杂的对象类型，而取出的时候不用做任何的数据转换
 * 3、字符串String、列表List、散列Hash、集合Set、有序集合ZSet
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void setString() {
        stringRedisTemplate.opsForValue().set("T-STRING-01", "字符串", 6 * 10, TimeUnit.SECONDS);
        String value = stringRedisTemplate.opsForValue().get("T-STRING");
        log.info("key: {}, value: {}", "T-STRING", value);
    }

    @Test
    public void setList() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        List<String> list2 = new ArrayList<>(Arrays.asList("aaa", "aaa", "bbb", "ccc"));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4));
        redisTemplate.opsForList().leftPush("T-LIST-01", list1);
        redisTemplate.opsForList().leftPush("T-LIST-01", list2);
        redisTemplate.opsForList().rightPush("T-LIST-01", list3);
        Object rightPop = redisTemplate.opsForList().rightPop("T-LIST-01");
        log.info("rightPop: {}", rightPop);
    }

    @Test
    public void setHash() {
        redisTemplate.opsForHash().put("T-HASH-01", "name", "tom");
        redisTemplate.opsForHash().put("T-HASH-01", "age", 26);
        redisTemplate.opsForHash().put("T-HASH-01", "class", "6");
        String name = (String) redisTemplate.opsForHash().get("T-HASH-01", "name");
        log.info("key: {}, value: {}", "name", name);

        Map<String, Object> map = new HashMap<>(3);
        map.put("name", "jack");
        map.put("age", 27);
        map.put("class", "1");
        redisTemplate.opsForHash().putAll("T-HASH-02", map);
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("T-HASH-02");
        log.info("entries: {}", JSON.toJSONString(entries));
    }

    @Test
    public void setSet() {
        redisTemplate.opsForSet().add("T-SET-01", "aaa", "bbb", "ccc", 1, 2, 3);
        Set<Object> members = redisTemplate.opsForSet().members("T-SET-01");
        log.info("members: {}", JSON.toJSONString(members));

        String[] s = new String[]{"111", "222"};
        Long num = redisTemplate.opsForSet().add("T-SET-02", s);
        log.info("num={}", num);
        Cursor<Object> scan = redisTemplate.opsForSet().scan("T-SET-02", ScanOptions.NONE);
        while (scan.hasNext()) {
            System.out.println(scan.next());
        }
    }

    @Test
    public void setZSet() {
        redisTemplate.opsForZSet().add("T-Z-SET-01", "a", 1.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("b", 2.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("c", 3.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("T-Z-SET-01", tuples));
        System.out.println(redisTemplate.opsForZSet().range("T-Z-SET-01", 0, -1));
    }

}
