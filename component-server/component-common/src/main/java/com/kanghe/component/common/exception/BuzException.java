package com.kanghe.component.common.exception;

import com.kanghe.component.common.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/18 23:53
 * @Description:
 **/
@Getter
public class BuzException extends RuntimeException {
    private Integer code;

    public BuzException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BuzException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
