package com.zredtea.TeaWIKI.common.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private final String code;
    private final String message;

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public BusinessException(ExceptionEnum exceptionEnum, String detail) {
        super(exceptionEnum.getMessage() + ": " + detail);
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage() + ": " + detail;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}