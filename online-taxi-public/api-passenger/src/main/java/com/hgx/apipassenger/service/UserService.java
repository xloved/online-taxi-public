package com.hgx.apipassenger.service;

import com.hgx.internalcomm.dto.PassengerUser;
import com.hgx.internalcomm.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){

        log.info("accessToken为"+accessToken);
        //解析accessToken获取手机号

        //根据手机号查询用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");


        return ResponseResult.success(passengerUser);
    }
}
