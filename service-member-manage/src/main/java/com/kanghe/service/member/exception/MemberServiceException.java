package com.kanghe.service.member.exception;

import com.kanghe.component.common.enums.ResultEnum;
import lombok.Getter;

@Getter
public class MemberServiceException extends RuntimeException {

    private Integer code;

    public MemberServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public MemberServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
