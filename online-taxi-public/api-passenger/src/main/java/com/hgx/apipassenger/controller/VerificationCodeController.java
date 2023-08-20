package com.hgx.apipassenger.controller;


import com.hgx.apipassenger.service.VerificationCodeService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//乘客验证码处理
@RestController
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    @PostMapping("/verification-code")
    public ResponseResult VerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();//定义接受的手机号码
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult VerificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("手机号为："+passengerPhone+"\n"+"验证码是："+verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
