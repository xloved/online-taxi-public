package com.hgx.apipassenger.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//校验手机号和验证码
@Data
public class CheckVerificationCodeDTO {

    //手机号码
    @NotBlank
    @Pattern(regexp = "^1[3,4,5,6,7,8,9]\\d{9}$", message = "请填写正确的手机号")
    private String passengerPhone;//此处的值要于测试文档定义的值的大小写保持一致

    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{6}$", message = "请填写6为数字的验证码")
    private String verificationCode;


}