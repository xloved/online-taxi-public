package com.hgx.apidriver.controller;

import com.hgx.apidriver.service.VerificationCodeService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 司机获取验证码
 * @Author huogaoxu
 * @Date 2023-06-03 21:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCode){
        String driverPhone = verificationCode.getDriverPhone();
        log.info("手机号为"+driverPhone);

        return verificationCodeService.verificationCode(driverPhone);
    }

    /**
     * 验证验证码
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult VerificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String driverPhone = verificationCodeDTO.getDriverPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("司机手机号为："+driverPhone+"\n"+"验证码是："+verificationCode);

        return verificationCodeService.checkCode(driverPhone,verificationCode);
    }
}
