package com.kanghe.component.rocketmq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 17:38
 * @Description: 自定义注解：监听MQ消息
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MQListener {

    String topic() default "";

    String tag() default "";

}
