package com.kanghe.service.file.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: W_jun1
 * @Date: 2019/5/18 22:50
 * @Description: Sftp工厂
 **/
@Component
@Slf4j
public class SftpFactory extends BasePooledObjectFactory<ChannelSftp> {

    @Autowired
    private SftpConfig config;

    /**
     * 创建Sftp对象
     *
     * @return
     * @throws Exception
     */
    @Override
    public ChannelSftp create() throws Exception {
        return connect(config.getHost(), Integer.parseInt(config.getPort()), config.getUsername(), config.getPassword());
    }

    private ChannelSftp connect(String host, int port, String username, String password) throws JSchException {
        log.info("sftp connect: host={},port={},username={},password={}", host, port, username, password);
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        Session sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        log.info("SFTP Session connected.");
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;
        log.info("Connected to {}", host);
        return sftp;
    }

    /**
     * 用PooledObject封装对象放入池中
     *
     * @param channelSftp
     * @return
     */
    @Override
    public PooledObject<ChannelSftp> wrap(ChannelSftp channelSftp) {
        return new DefaultPooledObject<>(channelSftp);
    }

    /**
     * 销毁Sftp对象
     *
     * @param sftpPooled
     */
    @Override
    public void destroyObject(PooledObject<ChannelSftp> sftpPooled) throws JSchException {
        if (sftpPooled == null) {
            return;
        }
        ChannelSftp sftp = sftpPooled.getObject();
        sftp.getSession().disconnect();
        sftp.quit();
        sftp.disconnect();
    }

    /**
     * 验证Sftp对象
     *
     * @param sftpPooled
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<ChannelSftp> sftpPooled) {
        ChannelSftp sftp = sftpPooled.getObject();
        return !sftp.isClosed();
    }
}
