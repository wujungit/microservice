package com.kanghe.component.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * @Author: W_jun1
 * @Date: 2019/4/23 17:38
 * @Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MQAnnotation {

    String value() default "";

}
