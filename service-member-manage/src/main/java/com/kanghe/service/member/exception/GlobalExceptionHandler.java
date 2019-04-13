package com.kanghe.service.member.exception;

import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/13 16:28
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 运行异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public BaseResult handleRuntimeException(RuntimeException e) {
        log.error("运行异常: {}", e.getMessage());
        return new BaseResult(Boolean.FALSE, ResultEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({MemberServiceException.class})
    public BaseResult handleBuzException(MemberServiceException e) {
        log.info("业务异常: {}", e.getMessage());
        return new BaseResult(Boolean.TRUE, e.getCode(), e.getMessage());
    }

}
