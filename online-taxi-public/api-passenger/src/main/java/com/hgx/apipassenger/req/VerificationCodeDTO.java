package com.hgx.apipassenger.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//验证码请求类
@Data
public class VerificationCodeDTO {

    //手机号码
    @NotBlank
    @Pattern(regexp = "^1[3,4,5,6,7,8,9]\\d{9}$", message = "请填写正确的手机号")
    private String passengerPhone;//此处的值要于测试文档定义的值的大小写保持一致

    private String verificationCode;

    // 司机手机号
    private String driverPhone;

}