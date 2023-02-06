package com.bugstart.edu.exception;

import com.bugstart.utils.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bugstart
 * @create 2023-01-26 1:14
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e){
        e.printStackTrace();
        return CommonResult.error().message("执行了全局异常处理...");
    }
}
