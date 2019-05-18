package com.kanghe.service.file.enums;

import lombok.Getter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/15 10:57
 * @Description:
 */
@Getter
public enum DataTypeEnum {
    /**
     * 数据类型
     */
    BYTES(0, "BYTES"),
    BASE64(1, "BASE64");

    private Integer code;
    private String msg;

    DataTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Integer getName(String msg) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (item.getMsg().equals(msg)) {
                return item.getCode();
            }
        }
        return 1;
    }

}
