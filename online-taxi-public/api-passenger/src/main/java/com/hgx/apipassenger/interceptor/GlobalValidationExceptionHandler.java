package com.hgx.apipassenger.interceptor;

import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description com.hgx.apipassenger.interceptor
 * @Author huogaoxu
 * @Date 2023-08-29 16:32
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult validationExceptionHandler(MethodArgumentNotValidException e){

        return ResponseResult.fail(CommonStatusEnum.VALIDATION_EXCEPTION.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
