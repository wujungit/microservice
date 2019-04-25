package com.kanghe.component.rocketmq.aspect;

import com.kanghe.component.rocketmq.annotation.MQListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 17:46
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class MQAspect {

    public static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Message>> MQ_CONTAINER = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.kanghe.component.rocketmq.annotation.MQListener)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public String pointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("pointcut...");
        joinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MQListener annotation = method.getAnnotation(MQListener.class);
        String topic = annotation.topic();
        String tag = annotation.tag();
        log.info("MQListener param: topic={},tag={}", topic, tag);
        MQ_CONTAINER.put("topic", new ConcurrentLinkedQueue<>());
        return "SUCCEED";
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        log.info("准备...");
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        log.info("结束...");
    }

}
