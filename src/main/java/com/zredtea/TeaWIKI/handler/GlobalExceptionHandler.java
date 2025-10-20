package com.zredtea.TeaWIKI.handler;

import com.zredtea.TeaWIKI.DTO.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleRuntimeException(RuntimeException e) {
        log.warn("运行时异常：{}", e.getMessage());
        return Result.error(400, e.getMessage());
    }

    /**
     * 处理Exception
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error(500, "系统内部错误，请联系管理员解决");
    }
}
