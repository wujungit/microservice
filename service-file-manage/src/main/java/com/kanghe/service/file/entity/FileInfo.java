package com.kanghe.service.file.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_file_info
 *
 * @author
 */
@Getter
@Setter
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

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
     * 文件相对路径
     */
    private String relativePath;

    /**
     * 文件绝对路径
     */
    private String absolutePath;

    /**
     * 文件来源（0-管理端，1-医生端，2-会员端，3-小程序）
     */
    private Integer fileOrigin;

    /**
     * 模型ID（用于业务标识）
     */
    private String modelId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 删除标记（0-正常，1-删除）
     */
    private Integer delFlag;
}