package com.kanghe.service.file.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 17:50
 * @Description: Sftp配置
 */
@Component
@Getter
public class SftpConfig {

    @Value("${file.sftp.host}")
    private String host;

    @Value("${file.sftp.port}")
    private String port;

    @Value("${file.sftp.username}")
    private String username;

    @Value("${file.sftp.password}")
    private String password;
}
