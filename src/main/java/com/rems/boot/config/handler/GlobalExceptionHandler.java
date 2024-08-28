package com.rems.boot.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rems.boot.core.LayResult;

/**
 * @Author qinj
 * @Date 2024/8/28
 * @Description 全局异常处理类
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LayResult<Void>> handleRuntimeException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(LayResult.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
