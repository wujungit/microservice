package com.kanghe.component.rocketmq.exception;

import com.kanghe.component.common.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: W_jun1
 * @Date: 2019/4/15 15:34
 * @Description:
 */
@Getter
public class RocketMQException extends RuntimeException {

    private Integer code;

    public RocketMQException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public RocketMQException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
