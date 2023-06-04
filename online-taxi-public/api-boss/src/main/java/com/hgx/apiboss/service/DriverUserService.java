package com.hgx.apiboss.service;

import com.hgx.apiboss.remote.ServiceDriverUserClient;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apiboss.service
 * @Author huogaoxu
 * @Date 2023-05-29 11:06
 * @Version 1.0
 **/
@Service
public class DriverUserService {

    // 把司机信息服务注入到DriverUserService
    @Resource
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addDriverUser(DriverUser driverUser){
        serviceDriverUserClient.addDriverUser(driverUser);
        return ResponseResult.success();
    }

    /**
     *
     * @param driverUser
     * @return
     */
    public ResponseResult updateDriverUser(DriverUser driverUser){
        serviceDriverUserClient.updateDriverUser(driverUser);
        return ResponseResult.success();
    }


}
