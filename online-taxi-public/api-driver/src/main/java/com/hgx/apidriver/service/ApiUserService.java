package com.hgx.apidriver.service;

import com.hgx.apidriver.remote.ServiceDriverUserClients;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 司机端service
 * @Author huogaoxu
 * @Date 2023-05-30 21:25
 * @Version 1.0
 **/
@Service
public class ApiUserService {

    @Resource
    private ServiceDriverUserClients serviceDriverUserClients;

    /**
     * 调用service-driver-user修改司机信息
     * @param driverUser
     * @return
     */
    public ResponseResult updateApiUser(DriverUser driverUser){
        serviceDriverUserClients.updateDriverUser(driverUser);
        return ResponseResult.success("");
    }

}
