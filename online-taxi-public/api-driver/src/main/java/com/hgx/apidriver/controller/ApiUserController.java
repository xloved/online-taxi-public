package com.hgx.apidriver.controller;

import com.hgx.apidriver.service.ApiUserService;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apidriver.controller
 * @Author huogaoxu
 * @Date 2023-05-30 21:21
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Resource
    private ApiUserService apiUserService;

    @PutMapping("/user")
    public ResponseResult updateApiUser(@RequestBody DriverUser driverUser){

        return apiUserService.updateApiUser(driverUser);
    }

}
