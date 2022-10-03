package com.hgx.apipassenger.service;

import com.hgx.internalcomm.dto.PassengerUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.dto.TokenResult;
import com.hgx.internalcomm.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken为"+accessToken);
        //解析accessToken获取手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String passengerPhone = tokenResult.getPassengerPhone();
        log.info("手机号为："+passengerPhone);
        //根据手机号查询用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");


        return ResponseResult.success(passengerUser);
    }
}
