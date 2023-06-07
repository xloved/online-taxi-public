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
 * @Description 司机端服务
 * @Author huogaoxu
 * @Date 2023-05-30 21:21
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Resource
    private ApiUserService apiUserService;

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateApiUser(@RequestBody DriverUser driverUser){

        return apiUserService.updateApiUser(driverUser);
    }

}
