package com.zredtea.TeaWIKI.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INPUT_IS_NULL("INPUT_001","输入不能为空!"),

    ILLEGAL_ARGUMENT("PARAM_001", "非法参数!"),
    MISSING_PARAMETER("PARAM_002", "缺少必要参数!"),
    PARAM_VALIDATION_FAILED("PARAM_003", "参数校验失败!"),

    TOKEN_NOT_LOGIN("AUTH_001","用户未登录!"),
    TOKEN_NOT_EQUAL("AUTH_002","cookie不匹配!"),

    USER_NOT_FOUND("USER_001","该用户不存在!"),
    USER_HAS_EXIST("USER_002","该用户已存在!"),
    USER_PWD_WRONG("USER_003","用户名或密码错误!"),
    USER_OLD_WRONG("USER_004","旧密码错误!"),

    TEACHER_NOT_FOUND("TEACHER_001","该教师不存在!"),

    COMMENT_NOT_FOUND("COMMENT_001","该评论不存在!"),
    COMMENT_HAS_EXIST("COMMENT_002","该评论已存在!"),
    COMMENT_CONNECT_NOT_FOUND("COMMENT_003","该评论所连接的教师或用户不存在!"),

    CVOTE_NOT_FOUND("CVOTE_001","尚未点赞或点踩过!"),
    CVOTE_HAS_LIKED("CVOTE_002","已经点过赞了!"),

    PERMISSION_ERROR("PERMISSION_001","非法用户!"),

    DATABASE_ERROR("DB_001","数据库操作时发生错误!");

    private final String code;
    private final String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}