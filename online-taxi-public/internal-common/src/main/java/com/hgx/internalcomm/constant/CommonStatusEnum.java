package com.hgx.internalcomm.constant;


import lombok.Getter;


/**
 * 定义返回值状态通用类
 */
public enum CommonStatusEnum {

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
