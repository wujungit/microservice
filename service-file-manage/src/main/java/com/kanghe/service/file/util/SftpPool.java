package com.kanghe.service.file.util;

import com.jcraft.jsch.ChannelSftp;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.common.exception.BuzException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BaseObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: W_jun1
 * @Date: 2019/5/18 23:09
 * @Description: Sftp连接池
 **/
@Component
@Slf4j
public class SftpPool extends BaseObjectPool<ChannelSftp> {

    private static final int DEFAULT_POOL_SIZE = 8;
    private static final int DEFAULT_POOL_INIT_SIZE = 3;

    @Autowired
    private SftpFactory sftpFactory;

    private ArrayBlockingQueue<ChannelSftp> sftpDeque;

    public SftpPool() {
        this.sftpDeque = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
    }

    /**
     * 初始化连接池
     *
     * @throws Exception
     */
    @PostConstruct
    private void initPool() throws Exception {
        for (int i = 0; i < DEFAULT_POOL_INIT_SIZE; i++) {
            addObject();
        }
    }

    /**
     * 从连接池中获取对象
     *
     * @return
     */
    @Override
    public ChannelSftp borrowObject() {
        try {
            ChannelSftp sftp = sftpDeque.take();
            if (ObjectUtils.isEmpty(sftp)) {
                sftp = sftpFactory.create();
                returnObject(sftp);
            } else if (!sftpFactory.validateObject(sftpFactory.wrap(sftp))) {
                invalidateObject(sftp);
                sftp = sftpFactory.create();
                returnObject(sftp);
            }
            return sftp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "从连接池中获取对象失败");
        }
    }

    /**
     * 返还对象到连接池中
     *
     * @param sftp
     * @throws Exception
     */
    @Overridee
    public void returnObject(ChannelSftp sftp) {
        try {
            if (sftp != null && !sftpDeque.offer(sftp, 3, TimeUnit.SECONDS)) {
                sftpFactory.destroyObject(sftpFactory.wrap(sftp));
            }
        } catch (Exception e) {
            e.printStackTrace();
       }
    }

    /**
     * 移除无效的对象
     *
     * @param sftp
     */
    @Override
    public void invalidateObject(ChannelSftp sftp) {
        try {
            sftp.cd("/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sftpDeque.remove(sftp);
        }
    }

    /**
     * 加入一个对象
     *
     * @throws Exception
     */
    @Override
    public void addObject() throws Exception {
        sftpDeque.offer(sftpFactory.create(), 3, TimeUnit.SECONDS);
    }

    /**
     * 关闭连接池
     */
    @Override
    public void close() {
        try {
            while (sftpDeque.iterator().hasNext()) {
                ChannelSftp sftp = sftpDeque.take();
                sftpFactory.destroyObject(sftpFactory.wrap(sftp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("close channelSftpDeque failed...{}", e);
        }
    }
}
