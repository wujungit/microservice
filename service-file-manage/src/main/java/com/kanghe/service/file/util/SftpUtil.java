package com.kanghe.service.file.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.common.exception.BuzException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: W_jun1
 * @Date: 2019/5/18 22:47
 * @Description: Sftp工具
 **/
@Component
@Slf4j
public class SftpUtil {

    @Autowired
    private SftpPool sftpPool;
    @Autowired
    private SftpFactory sftpFactory;

    public ChannelSftp getSftp() throws Exception {
        return sftpPool.borrowObject();
    }

    public ChannelSftp getNewSftp() throws Exception {
        return sftpFactory.create();
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param bytes     文件字节数组
     * @param fileName  文件保存名称
     * @return
     */
    public void upload(ChannelSftp sftp, String directory, byte[] bytes, String fileName) throws Exception {
        InputStream inputStream = null;
        try {
            sftp.cd(directory);
            if (null != bytes && bytes.length > 0) {
                inputStream = new ByteArrayInputStream(bytes);
            }
            sftp.put(inputStream, fileName);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建多层目录文件
     *
     * @param directory
     * @return Boolean
     */
    public void makeDirectory(ChannelSftp sftp, String directory) throws Exception {
        log.info("sftp={},directory={}", sftp, directory);
        if (isDirExist(sftp, directory)) {
            sftp.cd(directory);
            return;
        }
        String[] pathArray = directory.split("/");
        StringBuilder filePath = new StringBuilder("/");
        for (String path : pathArray) {
            if ("".equals(path)) {
                continue;
            }
            filePath.append(path).append("/");
            if (isDirExist(sftp, filePath.toString())) {
                sftp.cd(filePath.toString());
            } else {
                // 建立目录
                sftp.mkdir(filePath.toString());
                // 进入并设置为当前目录
                sftp.cd(filePath.toString());
            }
        }
        sftp.cd(directory);
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(ChannelSftp sftp, String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 下载文件
     *
     * @param downloadFilePath 下载的文件完整目录
     * @param saveFile         存在本地的路径
     */
    public void download(ChannelSftp sftp, String downloadFilePath, String saveFile) throws Exception {
        int i = downloadFilePath.lastIndexOf("/");
        if (i == -1) {
            throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "下载的文件完整目录格式正确");
        }
        String directory = downloadFilePath.substring(0, i);
        sftp.cd(directory);
        File file = new File(saveFile);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        sftp.get(downloadFilePath.substring(i + 1), fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(ChannelSftp sftp, String directory, String deleteFile) throws Exception {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }
}
