package com.hgx.apipassenger.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//使用手机号获取验证码
@Data
public class SendVerificationCodeDTO {

    //手机号码
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3,4,5,6,7,8,9]\\d{9}$", message = "请填写正确的手机号")
    private String passengerPhone; //此处的值要于测试文档定义的值的大小写保持一致


}