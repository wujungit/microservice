package com.kanghe.service.file.enums;

import lombok.Getter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 11:57
 * @Description:
 */
@Getter
public enum FileTypeEnum {
    /**
     * 文件类型
     */
    FILE(0, "FILE"),
    IMG(1, "IMG"),
    TEMP(2, "TEMP");

    private Integer code;
    private String msg;

    FileTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
