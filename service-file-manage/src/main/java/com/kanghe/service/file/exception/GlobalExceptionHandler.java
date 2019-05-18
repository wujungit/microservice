package com.kanghe.service.file.exception;

import com.alibaba.fastjson.JSONObject;
import com.kanghe.component.common.base.BaseResult;
import com.kanghe.component.common.enums.ResultEnum;
import com.kanghe.component.common.exception.BuzException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理类
 *
 * @Description
 * @Author HHJ
 * @Date 2019-03-21 15:25
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name:appName}")
    private String srvName;

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({BuzException.class})
    public BaseResult handleBuzException(BuzException e) {
        log.info("服务" + srvName + "业务异常:", e);
        Integer code = e.getCode();
        if (code == null) {
            code = ResultEnum.SYSTEM_ERROR.getCode();
        }
        return new BaseResult<>(Boolean.FALSE, new JSONObject(), code, e.getMessage());
    }

    /**
     * 校验错误处理
     *
     * @param e 错误信息集合
     * @return 错误信息
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
                log.info("服务{}数据校验错误 : object={},field={},errorMessage={}", srvName, fieldError.getObjectName(), fieldError.getField(), thisMsg);
                return new BaseResult().failure(thisMsg);
            }
        }
        return new BaseResult().failure(e.getMessage());
    }

}
