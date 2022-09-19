package com.hgx.servicepassengeruser.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicepassengeruser.dto.PassengerUser;
import com.hgx.servicepassengeruser.mapper.PassengerUserMapper;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    PassengerUserMapper passengerUserMapper;

    @Resource
    PassengerUserMapper passengerUserMapper;

    //用户登录与注册
    public ResponseResult loginOrRegister(String passengerPhone){

        System.out.println("user serice被调用，手机号"+passengerPgone);
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPgone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size()==0?"无记录":passengerUsers.get(0).getPassengerPhone());


        //判断用户是否已存在
        if(passengerUsers.size() == 0){
            //如果不存在插入用户信息
            PassengerUser passengerUser = new PassengerUser( );
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerPhone(passengerPhone);
            LocalDateTime nowdata = LocalDateTime.now();
            passengerUser.setGmtCreate(nowdata);
            passengerUser.setGmtModified(nowdata);
            passengerUser.setPassengerGender((byte)0);
            passengerUser.setState((byte)0);
            passengerUserMapper.insert(passengerUser);
        }


        return ResponseResult.success();
    }
}
