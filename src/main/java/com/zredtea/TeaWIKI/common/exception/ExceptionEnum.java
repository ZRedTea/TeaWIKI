package com.zredtea.TeaWIKI.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    USER_NOT_FOUND("USER_001", "此用户不存在!"),
    USER_ALREADY_EXISTS("USER_002", "此用户已存在!"),
    USER_PASSWORD_ERROR("USER_003", "账号或密码错误!"),
    USER_STATUS_DISABLED("USER_004", "用户已被禁用!"),

    TEACHER_NOT_FOUND("TEACHER_001", "此教师不存在!"),
    TEACHER_ALREADY_EXISTS("TEACHER_002", "教师已存在!"),

    COMMENT_NOT_FOUND("COMMENT_001", "评论不存在!"),
    COMMENT_PERMISSION_DENIED("COMMENT_002", "无权操作此评论!"),
    COMMENT_CONTENT_EMPTY("COMMENT_003", "评论内容不能为空!"),

    ILLEGAL_ARGUMENT("PARAM_001", "非法参数!"),
    MISSING_PARAMETER("PARAM_002", "缺少必要参数!"),
    PARAM_VALIDATION_FAILED("PARAM_003", "参数校验失败!"),

    UNAUTHORIZED("AUTH_001", "未登录或登录已过期!"),
    FORBIDDEN("AUTH_002", "权限不足!"),
    TOKEN_INVALID("AUTH_003", "令牌无效!"),

    SYSTEM_ERROR("SYS_001", "系统繁忙，请稍后重试!"),
    DATA_ACCESS_ERROR("SYS_002", "数据访问异常!"),
    FILE_UPLOAD_ERROR("SYS_003", "文件上传失败!");

    private final String code;
    private final String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}