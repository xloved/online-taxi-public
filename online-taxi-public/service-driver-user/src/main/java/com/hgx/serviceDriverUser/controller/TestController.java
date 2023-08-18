package com.hgx.serviceDriverUser.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.mapper.DriverUserMapper;
import com.hgx.serviceDriverUser.service.DriverUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 测试类
 * @Author huogaoxu
 * @Date 2023-05-28 20:35
 * @Version 1.0
 **/
@RestController
public class TestController {

    @Resource
    private DriverUserService driverUserService;
    @GetMapping ("/test")
    public String test(){
        return "service-driver-user";
    }

    @GetMapping("/test_db")
    public ResponseResult testDb(){
        return driverUserService.testGetDriverUser();
    }


    //测试driverUsermapper文件能否使用
    @Resource
    DriverUserMapper driverUserMapper;
//    @GetMapping("/testSelect")
//    public int testSelect(String args){
//       return  driverUserMapper.selectDriverUserCountByCityCode("1");
//
//    }
//
//    @GetMapping("/test-xml")
//    public int select(String cityCode){
//        return  driverUserMapper.selectDriverUserCountByCityCode(cityCode);
//
//    }
}
