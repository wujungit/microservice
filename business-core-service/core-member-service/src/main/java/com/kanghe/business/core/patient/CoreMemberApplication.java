package com.kanghe.business.core.patient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:49
 * @Description:
 */
@SpringBootApplication
public class CoreMemberApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CoreMemberApplication.class).run(args);
    }

}
