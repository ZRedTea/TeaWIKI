// com.zredtea.TeaWIKI.common.exception.ServerException
package com.zredtea.TeaWIKI.common.exception;

import lombok.Getter;

/**
 * 服务器异常 - 服务器内部错误 (500)
 * 继承 Exception，用于需要检查的异常
 */
public class ServerException extends RuntimeException {

    @Getter
    private final String code;
    private final String message;

    public ServerException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public ServerException(ExceptionEnum exceptionEnum, String detail) {
        super(exceptionEnum.getMessage() + ": " + detail);
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage() + ": " + detail;
    }

    public ServerException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
