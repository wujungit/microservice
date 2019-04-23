package com.kanghe.component.rocketmq.aspect;

import com.kanghe.component.rocketmq.annotation.MQAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 17:46
 * @Description:
 */
@Component
@Aspect
public class MQAspect {

    @Pointcut("@annotation(MQListen)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MQAnnotation annotation = method.getAnnotation(MQAnnotation.class);
        String value = annotation.value();
        System.out.println("准备" + value);
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MQAnnotation annotation = method.getAnnotation(MQAnnotation.class);
        String value = annotation.value();
        System.out.println("结束" + value);
    }

}
