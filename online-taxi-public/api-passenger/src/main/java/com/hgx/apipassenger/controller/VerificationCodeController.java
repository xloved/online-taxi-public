package com.hgx.apipassenger.controller;

import com.hgx.apipassenger.request.VerificationCodeDTO;
import com.hgx.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//乘客验证码处理
@RestController
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;


    @GetMapping("/verification-code")
    public String VerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //System.out.println(verificationCodeDTO.getPassengerPhone()+"123");
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号是："+passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }
}
