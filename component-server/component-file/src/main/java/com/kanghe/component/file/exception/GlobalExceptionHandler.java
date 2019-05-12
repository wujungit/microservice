package com.kanghe.component.file.exception;

import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @ExceptionHandler({FileServiceException.class})
    public BaseResult handleBuzException(FileServiceException e) {
        log.info("业务异常: {}", e.getMessage());
        return new BaseResult(Boolean.TRUE, e.getCode(), e.getMessage());
    }

    /**
     * 请求参数校验
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult validationBodyException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError p : errors) {
                FieldError fieldError = (FieldError) p;
                String thisMsg = fieldError.getDefaultMessage();
                log.info("请求参数校验错误: name={}, field={}, msg={}", fieldError.getObjectName(), fieldError.getField(), thisMsg);
                return new BaseResult(Boolean.FALSE, ResultEnum.INVALID_PARAM.getCode(), e.getMessage());
            }
        }
        return new BaseResult(Boolean.FALSE, ResultEnum.INVALID_PARAM.getCode(), e.getMessage());
    }

}
