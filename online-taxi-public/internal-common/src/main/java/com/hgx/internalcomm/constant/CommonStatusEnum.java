package com.hgx.internalcomm.constant;


import lombok.Getter;


/**
 * 定义返回值状态通用类
 */
public enum CommonStatusEnum {
    //定义验证码错误
    VERIFICATION_CODE_ERRROR(1099,"验证码不正确"),

    //token类提示，1100-1199
    TOKEN_ERROR(1199,"token错误"),

    //定义验证码错误
    VERIFICATION_CODE_ERRROR(1099,"验证码不正确"),

    //定义返回成功
    SUCCESS(1,"success"),
    //定义返回失败
    FAIL(0,"fail");

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
