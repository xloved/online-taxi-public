package com.hgx.servicepassengeruser.controller;



import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import com.hgx.servicepassengeruser.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登与注册
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

         String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号为"+passengerPhone);
        return userService.loginOrRegister(passengerPhone);

    }


    @GetMapping("/selectuser/{phone}")
    public ResponseResult getSelectUser(@PathVariable("phone") String passengerPhone){

        System.out.println("手机号为"+passengerPhone);
        return userService.getBySelectUser(passengerPhone);

    }
}
