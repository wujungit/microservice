package com.kanghe.service.file.service;

import com.kanghe.component.common.dto.file.dto.UploadFileBatchDTO;
import com.kanghe.component.common.dto.file.dto.UploadFileDTO;
import com.kanghe.component.common.dto.file.vo.FileInfoVO;

import java.util.List;

/**
 * @Author: W_jun1
 * @Date: 2019/5/13 11:02
 * @Description: 文件操作
 **/

public interface IFileService {

    /**
     * 通过模型ID获取文件信息
     *
     * @param modelId
     * @return
     */
    public FileInfoVO getFileInfoByModelId(String modelId);

    /**
     * 上传文件
     *
     * @param dto
     * @return
     */
    public FileInfoVO uploadFile(UploadFileDTO dto);

    /**
     * 批量上传文件
     *
     * @param dto
     * @return
     */
    public List<FileInfoVO> uploadFileBatch(UploadFileBatchDTO dto);

    /**
     * 下载文件
     *
     * @param fileCode  文件编码
     * @param directory 下载文件目录
     * @return FileInfoVO
     */
    public FileInfoVO downloadFile(String fileCode, String directory);

    /**
     * 批量下载文件
     *
     * @param fileCodes 文件编码列表
     * @param directory 下载文件目录
     * @return List<FileInfoVO>
     */
    public List<FileInfoVO> downloadFileBatch(List<String> fileCodes, String directory);

    /**
     * 删除文件
     *
     * @param fileCode 文件编码
     * @return Boolean
     */
    public Boolean deleteFile(String fileCode);

    /**
     * 批量删除文件
     *
     * @param fileCodes 文件编码列表
     * @return Boolean
     */
    public Boolean deleteFileBatch(List<String> fileCodes);

}
