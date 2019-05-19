package com.kanghe.service.file.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.kanghe.component.common.dto.file.dto.FileDTO;
import com.kanghe.component.common.dto.file.dto.UploadFileBatchDTO;
import com.kanghe.component.common.dto.file.dto.UploadFileDTO;
import com.kanghe.component.common.dto.file.vo.FileInfoVO;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.common.exception.BuzException;
import com.kanghe.component.common.util.CommonCodeUtil;
import com.kanghe.component.common.util.DateUtils;
import com.kanghe.component.common.util.MD5Util;
import com.kanghe.service.file.entity.FileInfo;
import com.kanghe.service.file.enums.DataTypeEnum;
import com.kanghe.service.file.enums.FileTypeEnum;
import com.kanghe.service.file.mapper.FileInfoMapper;
import com.kanghe.service.file.service.IFileService;
import com.kanghe.service.file.util.SftpPool;
import com.kanghe.service.file.util.SftpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: W_jun1
 * @Date: 2019/5/13 11:02
 * @Description:
 **/
@Service
@Slf4j
public class FileServiceImpl implements IFileService {

    private static final String FILE_PREFIX = "FILE";
    private static final int FILE_BYTES_MAX = 50 * 1024 * 1024;

    @Value("${file.sftp.directory.relative}")
    private String baseRelativeDir;
    @Value("${file.sftp.directory.absolute}")
    private String baseAbsoluteDir;
    @Value("${file.sftp.domain}")
    private String domain;

    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Autowired
    private SftpPool sftpPool;
    @Autowired
    private SftpUtil sftpUtil;

    @Override
    public FileInfoVO getFileInfoByModelId(String modelId) {
        // 参数校验
        if (StringUtils.isBlank(modelId)) {
            throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：模型ID");
        }
        FileInfo fileInfo = fileInfoMapper.selectByModelId(modelId);
        if (null == fileInfo) {
            return null;
        }
        return getFileInfoVO(fileInfo);
    }

    @Override
    public List<FileInfoVO> uploadFileBatch(UploadFileBatchDTO dto) {
        ChannelSftp sftp = null;
        try {
            List<FileDTO> fileDTOList = dto.getFileDTOList();
            String operator = dto.getOperator();
            Integer origin = dto.getOrigin();
            // 校验文件大小，最大50M
            log.info("获取文件字节数据: start={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            for (FileDTO fileDTO : fileDTOList) {
                Integer dataType = fileDTO.getDataType();
                byte[] bytes = fileDTO.getBytes();
                String base64 = fileDTO.getBase64();
                // 获取文件字节数据
                if (DataTypeEnum.BASE64.getCode().equals(dataType)) {
                    bytes = base64ToBytes(base64);
                }
                if (bytes.length > FILE_BYTES_MAX) {
                    throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "批量文件上传失败：单个文件最大不超过50M");
                }
            }
            log.info("获取文件字节数据: end={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            List<FileInfoVO> result = new ArrayList<>();
            List<FileInfo> list = new ArrayList<>();
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            for (FileDTO fileDTO : fileDTOList) {
                Integer dataType = fileDTO.getDataType();
                byte[] bytes = fileDTO.getBytes();
                String base64 = fileDTO.getBase64();
                String fileRealName = fileDTO.getFileRealName();
                Integer fileType = fileDTO.getFileType();
                String fileSuffix = fileDTO.getFileSuffix();
                String modelId = fileDTO.getModelId();
                // 获取文件字节数据
                if (DataTypeEnum.BASE64.getCode().equals(dataType)) {
                    bytes = base64ToBytes(base64);
                }
                // 获取文件md5值
                String fileMd5 = MD5Util.getMD5String(bytes);
                // 生成待上传的文件名
                String yyyyMMdd = DateUtils.getCurrentTimeStr("yyyyMMdd");
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String fileName = getUTF8StringFromGBKString(fileRealName.substring(0, fileRealName.lastIndexOf("."))) + "_" + yyyyMMdd + "_" + uuid + "." + fileSuffix;
                // 获取文件相对路径和绝对路径
                String uploadDir = getUploadDirectory(fileType);
                String relativeDir = baseRelativeDir + uploadDir;
                String absoluteDir = baseAbsoluteDir + uploadDir;
                String relativePath = relativeDir + "/" + fileName;
                String absolutePath = absoluteDir + "/" + fileName;
                // 创建文件目录
                Boolean makeDir = sftpUtil.makeDirectory(sftp, relativeDir);
                if (!makeDir) {
                    throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "创建文件目录失败");
                }
                // 上传文件
                log.info("上传文件: start={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
                boolean upload = sftpUtil.upload(sftp, relativeDir, bytes, fileName);
                log.info("上传文件: end={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
                if (!upload) {
                    throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "上传文件失败");
                }
                FileInfo fileInfo = new FileInfo();
                fileInfo.setCode(CommonCodeUtil.generateRandomCoded(FILE_PREFIX));
                fileInfo.setFileName(fileName);
                fileInfo.setFileRealName(fileRealName);
                fileInfo.setFileType(fileType);
                fileInfo.setFileSuffix(fileSuffix);
                fileInfo.setFileMd5(fileMd5);
                fileInfo.setFileSize(bytes.length / 1024);
                fileInfo.setFileUrl(domain + uploadDir + "/" + fileName);
                fileInfo.setRelativePath(relativePath);
                fileInfo.setAbsolutePath(absolutePath);
                fileInfo.setFileOrigin(origin);
                fileInfo.setModelId(modelId);
                fileInfo.setOperator(operator);
                list.add(fileInfo);
                result.add(getFileInfoVO(fileInfo));
            }
            if (list.size() > 0) {
                fileInfoMapper.insertBatch(list);
            }
            return result;
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    @Override
    public FileInfoVO uploadFile(UploadFileDTO dto) {
        ChannelSftp sftp = null;
        try {
            Integer dataType = dto.getDataType();
            byte[] bytes = dto.getBytes();
            String base64 = dto.getBase64();
            String fileRealName = dto.getFileRealName();
            Integer fileType = dto.getFileType();
            String fileSuffix = dto.getFileSuffix();
            String modelId = dto.getModelId();
            String operator = dto.getOperator();
            Integer origin = dto.getOrigin();
            // 获取文件字节数据
            log.info("获取文件字节数据: start={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            if (DataTypeEnum.BASE64.getCode().equals(dataType)) {
                bytes = base64ToBytes(base64);
            }
            log.info("获取文件字节数据: end={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            // 校验文件大小，最大50M
            if (bytes.length > FILE_BYTES_MAX) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "文件上传失败：单个文件最大不超过50M");
            }
            // 获取文件md5值
            String fileMd5 = MD5Util.getMD5String(bytes);
            // 生成待上传的文件名
            String yyyyMMdd = DateUtils.getCurrentTimeStr("yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = getUTF8StringFromGBKString(fileRealName.substring(0, fileRealName.lastIndexOf("."))) + "_" + yyyyMMdd + "_" + uuid + "." + fileSuffix;
            // 获取文件相对路径和绝对路径
            String uploadDir = getUploadDirectory(fileType);
            String relativeDir = baseRelativeDir + uploadDir;
            String absoluteDir = baseAbsoluteDir + uploadDir;
            String relativePath = relativeDir + "/" + fileName;
            String absolutePath = absoluteDir + "/" + fileName;
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            // 创建文件目录
            log.info("relativeDir={}", relativeDir);
            Boolean makeDir = sftpUtil.makeDirectory(sftp, relativeDir);
            if (!makeDir) {
                throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "创建文件目录失败");
            }
            // 上传文件
            log.info("上传文件: start={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            boolean upload = sftpUtil.upload(sftp, relativeDir, bytes, fileName);
            log.info("上传文件: end={}", DateUtils.getCurrentTimeStr("HH:mm:ss.sss"));
            if (!upload) {
                throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "上传文件失败");
            }
            FileInfo fileInfo = new FileInfo();
            fileInfo.setCode(CommonCodeUtil.generateRandomCoded(FILE_PREFIX));
            fileInfo.setFileName(fileName);
            fileInfo.setFileRealName(fileRealName);
            fileInfo.setFileType(fileType);
            fileInfo.setFileSuffix(fileSuffix);
            fileInfo.setFileMd5(fileMd5);
            fileInfo.setFileSize(bytes.length / 1024);
            fileInfo.setFileUrl(domain + uploadDir + "/" + fileName);
            fileInfo.setRelativePath(relativePath);
            fileInfo.setAbsolutePath(absolutePath);
            fileInfo.setModelId(modelId);
            fileInfo.setFileOrigin(origin);
            fileInfo.setOperator(operator);
            fileInfoMapper.insertSelective(fileInfo);
            return getFileInfoVO(fileInfo);
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    private static String getUTF8StringFromGBKString(String gbkStr) {
        return new String(getUTF8BytesFromGBKString(gbkStr), StandardCharsets.UTF_8);
    }

    private static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }

    private byte[] base64ToBytes(String base64) {
        byte[] bytes = new byte[0];
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bytes = decoder.decodeBuffer(base64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private FileInfoVO getFileInfoVO(FileInfo existFileInfo) {
        FileInfoVO vo = new FileInfoVO();
        vo.setFileCode(existFileInfo.getCode());
        vo.setFileName(existFileInfo.getFileName());
        vo.setFileRealName(existFileInfo.getFileRealName());
        vo.setFileType(existFileInfo.getFileType());
        vo.setFileSuffix(existFileInfo.getFileSuffix());
        vo.setFileMd5(existFileInfo.getFileMd5());
        vo.setFileSize(existFileInfo.getFileSize());
        vo.setFileUrl(existFileInfo.getFileUrl());
        vo.setModelId(existFileInfo.getModelId());
        return vo;
    }

    @Override
    public FileInfoVO downloadFile(String fileCode, String directory) {
        ChannelSftp sftp = null;
        try {
            // 参数校验
            if (StringUtils.isBlank(fileCode)) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：文件编码");
            }
            if (StringUtils.isBlank(directory)) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：下载文件目录");
            }
            // 判断文件是否存在
            FileInfo existFileInfo = fileInfoMapper.selectByCode(fileCode);
            if (null == existFileInfo) {
                throw new BuzException(ResultEnum.DATA_NOT_FOUND.getCode(), "下载失败：文件不存在");
            }
            String relativePath = existFileInfo.getRelativePath();
            String fileRealName = existFileInfo.getFileRealName();
            String saveFile = directory + "/" + fileRealName;
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            Boolean download = sftpUtil.download(sftp, relativePath, saveFile);
            if (!download) {
                throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "下载文件失败");
            }
            return getFileInfoVO(existFileInfo);
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    @Override
    public List<FileInfoVO> downloadFileBatch(List<String> fileCodes, String directory) {
        ChannelSftp sftp = null;
        try {
            // 参数校验
            if (null == fileCodes || fileCodes.size() == 0) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：文件编码列表");
            }
            if (StringUtils.isBlank(directory)) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：下载文件目录");
            }
            // 判断文件列表是否存在
            List<FileInfo> fileInfos = fileInfoMapper.selectByCodes(fileCodes);
            if (null == fileInfos || fileInfos.size() == 0) {
                throw new BuzException(ResultEnum.DATA_NOT_FOUND.getCode(), "批量下载文件失败：文件不存在");
            }
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            List<FileInfoVO> result = new ArrayList<>();
            for (FileInfo fileInfo : fileInfos) {
                String relativePath = fileInfo.getRelativePath();
                String fileRealName = fileInfo.getFileRealName();
                String saveFile = directory + "/" + fileRealName;
                Boolean download = sftpUtil.download(sftp, relativePath, saveFile);
                if (!download) {
                    throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "下载文件失败");
                }
                result.add(getFileInfoVO(fileInfo));
            }
            return result;
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    @Override
    public Boolean deleteFile(String fileCode) {
        ChannelSftp sftp = null;
        try {
            // 参数校验
            if (StringUtils.isBlank(fileCode)) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：文件编码");
            }
            // 判断文件是否存在
            FileInfo existFileInfo = fileInfoMapper.selectByCode(fileCode);
            if (null == existFileInfo) {
                throw new BuzException(ResultEnum.DATA_NOT_FOUND.getCode(), "删除失败：文件不存在");
            }
            String relativePath = existFileInfo.getRelativePath();
            String directory = relativePath.substring(0, relativePath.lastIndexOf("/"));
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            boolean delete = sftpUtil.delete(sftp, directory, existFileInfo.getFileName());
            if (!delete) {
                throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "删除文件失败");
            }
            // 记录表t_file_info
            fileInfoMapper.logicDelete(existFileInfo.getCode());
            return Boolean.TRUE;
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    @Override
    public Boolean deleteFileBatch(List<String> fileCodes) {
        ChannelSftp sftp = null;
        try {
            // 参数校验
            if (null == fileCodes || fileCodes.size() == 0) {
                throw new BuzException(ResultEnum.INVALID_PARAM.getCode(), "参数不能为空：文件编码列表");
            }
            // 判断文件列表是否存在
            List<FileInfo> fileInfos = fileInfoMapper.selectByCodes(fileCodes);
            if (null == fileInfos || fileInfos.size() == 0) {
                throw new BuzException(ResultEnum.DATA_NOT_FOUND.getCode(), "删除失败：文件不存在");
            }
            // 获取Sftp连接
            sftp = sftpPool.borrowObject();
            for (FileInfo fileInfo : fileInfos) {
                String relativePath = fileInfo.getRelativePath();
                String directory = relativePath.substring(0, relativePath.lastIndexOf("/"));
                boolean delete = sftpUtil.delete(sftp, directory, fileInfo.getFileName());
                if (!delete) {
                    throw new BuzException(ResultEnum.SYSTEM_ERROR.getCode(), "删除文件失败");
                }
                // 记录表t_file_info
                fileInfoMapper.logicDelete(fileInfo.getCode());
            }
            return Boolean.TRUE;
        } finally {
            sftpUtil.returnSftp(sftp);
        }
    }

    /**
     * 获取上传目录
     *
     * @param fileType 文件类型（0-文件，1-图像，2-临时文件）
     * @return String
     */
    private String getUploadDirectory(Integer fileType) {
        String yyyyMMdd = DateUtils.getCurrentTimeStr("yyyyMMdd");
        if (FileTypeEnum.FILE.getCode().equals(fileType)) {
            return "/file/" + yyyyMMdd;
        } else if (FileTypeEnum.IMG.getCode().equals(fileType)) {
            return "/img/" + yyyyMMdd;
        } else if (FileTypeEnum.TEMP.getCode().equals(fileType)) {
            return "/temp/" + yyyyMMdd;
        }
        return null;
    }
}
