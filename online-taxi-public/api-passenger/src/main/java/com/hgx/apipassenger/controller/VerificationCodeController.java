package com.hgx.apipassenger.controller;


import com.hgx.apipassenger.req.CheckVerificationCodeDTO;
import com.hgx.apipassenger.req.SendVerificationCodeDTO;
import com.hgx.apipassenger.service.VerificationCodeService;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//乘客验证码处理
@RestController
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    /**
     * 发送验证码
     */
    @PostMapping("/verification-code")
    public ResponseResult VerificationCode(@Validated @RequestBody SendVerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();//定义接受的手机号码
        return verificationCodeService.generatorCode(passengerPhone);
    }

    /**
     * 校验验证码
     * @param checkVerificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult VerificationCodeCheck(@RequestBody CheckVerificationCodeDTO checkVerificationCodeDTO) {

        String passengerPhone = checkVerificationCodeDTO.getPassengerPhone();
        String verificationCode = checkVerificationCodeDTO.getVerificationCode();
        System.out.println("手机号为："+passengerPhone+"\n"+"验证码是："+verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
