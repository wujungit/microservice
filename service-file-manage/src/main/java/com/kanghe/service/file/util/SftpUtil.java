package com.kanghe.service.file.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import lombok.extern.slf4j.Slf4j;
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

    private ChannelSftp sftp;

    public SftpUtil() {
    }

    private ChannelSftp borrowSftp() throws Exception {
        SftpFactory factory = new SftpFactory();
        SftpPool pool = new SftpPool(factory);
        return pool.borrowObject();
    }

    public void returnSftp() throws Exception {
        SftpFactory factory = new SftpFactory();
        SftpPool pool = new SftpPool(factory);
        pool.returnObject(sftp);
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param bytes     文件字节数组
     * @param fileName  文件保存名称
     * @return
     */
    public boolean upload(String directory, byte[] bytes, String fileName) {
        InputStream inputStream = null;
        try {
            sftp.cd(directory);
            if (null != bytes && bytes.length > 0) {
                inputStream = new ByteArrayInputStream(bytes);
            }
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
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
    public Boolean makeDirectory(String directory) {
        try {
            if (isDirExist(directory)) {
                sftp.cd(directory);
                return true;
            }
            String[] pathArray = directory.split("/");
            StringBuilder filePath = new StringBuilder("/");
            for (String path : pathArray) {
                if ("".equals(path)) {
                    continue;
                }
                filePath.append(path).append("/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(directory);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if ("no such file".equals(e.getMessage().toLowerCase())) {
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
    public Boolean download(String downloadFilePath, String saveFile) {
        try {
            int i = downloadFilePath.lastIndexOf("/");
            if (i == -1) {
                return false;
            }
            String directory = downloadFilePath.substring(0, i);
            sftp.cd(directory);
            File file = new File(saveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sftp.get(downloadFilePath.substring(i + 1), fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public boolean delete(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
