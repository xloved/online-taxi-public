package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServicePassengerUserClient;
import com.hgx.internalcomm.dto.PassengerUser;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.dto.TokenResult;
import com.hgx.internalcomm.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserService {

    @Resource
     ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken为"+accessToken);
        //解析accessToken获取手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String passengerPhone = tokenResult.getPhone();
        log.info("手机号为："+passengerPhone);
        //根据手机号查询用户信息
        ResponseResult<PassengerUser> phone = servicePassengerUserClient.getUserByPhone(passengerPhone);

        return ResponseResult.success(phone.getData());
    }
}
