package com.kanghe.component.file.exception;

import com.kanghe.component.common.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: W_jun1
 * @Date: 2019/5/12 11:04
 * @Description:
 **/
@Getter
public class FileServiceException extends RuntimeException {

    private Integer code;

    public FileServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public FileServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}