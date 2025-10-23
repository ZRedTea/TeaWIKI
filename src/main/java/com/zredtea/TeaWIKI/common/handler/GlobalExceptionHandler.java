package com.zredtea.TeaWIKI.common.handler;

import com.zredtea.TeaWIKI.DTO.Result;
import com.zredtea.TeaWIKI.common.exception.BusinessException;
import com.zredtea.TeaWIKI.common.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理用户输入错误抛出的BusinessException
     * 返回400
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e) {
        log.warn("抛出BusinessException:{}", e.getMessage());
        return Result.error(400, e.getMessage());
    }

    /**
     * 处理内部错误抛出的ServerException
     * 返回500
     */
    @ExceptionHandler(ServerException.class)
    public Result<Object> handleServerException(ServerException e) {
        log.warn("抛出ServerException:{}", e.getMessage());
        return Result.error(500, e.getMessage());
    }

    /**
     * 处理其他问题所造成的Exception
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        log.error("系统发生异常：", e);
        return Result.error(500, "系统内部错误，请联系管理员解决");
    }
}
