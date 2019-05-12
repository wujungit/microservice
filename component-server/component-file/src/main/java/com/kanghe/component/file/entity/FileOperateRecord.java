package com.kanghe.component.file.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * t_file_operate_record
 *
 * @author
 */
@Getter
@Setter
public class FileOperateRecord implements Serializable {

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
     * 文件类型
     */
    private Boolean fileType;

    /**
     * 文件大小，单位：KB
     */
    private Integer fileSize;

    /**
     * 文件URL
     */
    private String fileUrl;

    /**
     * 文件来源
     */
    private String fileOrigin;

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

}