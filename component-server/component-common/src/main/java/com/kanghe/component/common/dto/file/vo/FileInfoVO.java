package com.kanghe.component.common.dto.file.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: W_jun1
 * @Date: 2019/5/14 14:51
 * @Description:
 */
@Getter
@Setter
public class FileInfoVO implements Serializable {
    /**
     * 文件编码
     */
    private String fileCode;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件真实名称
     */
    private String fileRealName;

    /**
     * 文件类型（0-文件，1-图像，2-临时文件）
     */
    private Integer fileType;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件md5值
     */
    private String fileMd5;

    /**
     * 文件大小，单位：KB
     */
    private Integer fileSize;

    /**
     * 文件URL
     */
    private String fileUrl;

    /**
     * 模型ID（用于业务标识）
     */
    private String modelId;
}
