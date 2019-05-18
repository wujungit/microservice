package com.kanghe.service.file.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 17:50
 * @Description: Sftp配置
 */
@Configuration
@PropertySource(value = "classpath:config/sftp.properties", encoding = "UTF-8")
public class SftpConfig {

    @Autowired
    public SftpConfig(Environment env) {
        SftpConfig.environment = env;
    }

    private static Environment environment;

    public static String get(String key) {
        return environment.getProperty(key);
    }
}
