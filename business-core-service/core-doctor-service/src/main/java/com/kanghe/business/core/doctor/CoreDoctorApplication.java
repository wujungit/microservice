package com.kanghe.business.core.doctor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/12 09:48
 * @Description:
 */
@SpringBootApplication
public class CoreDoctorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CoreDoctorApplication.class).run(args);
    }

}
