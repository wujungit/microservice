package com.kanghe.component.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    /**
     * 系统返回数据
     */
    SUCCESS(200, "请求成功"),
    SYSTEM_ERROR(500, "系统异常"),
    INVALID_PARAM(1001, "参数不合法"),
    DATA_NOT_FOUND(1002, "数据不存在"),
    DATA_HAS_EXISTS(1003, "数据已存在"),
    MQ_EXECUTE_ERROR(1004, "MQ执行失败");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}