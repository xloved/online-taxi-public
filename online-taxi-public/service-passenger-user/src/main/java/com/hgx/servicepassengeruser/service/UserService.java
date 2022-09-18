package com.hgx.servicepassengeruser.service;

import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //用户登录与注册
    public ResponseResult loginOrRegister(String passengerPgone){

        System.out.println("user serice被调用，手机号"+passengerPgone);
        //根据手机号查询用户
        //判断用户是否已存在
        //如果不存在插入用户信息

        return ResponseResult.success();
    }
}
