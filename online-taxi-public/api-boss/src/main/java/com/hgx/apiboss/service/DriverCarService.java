package com.hgx.apiboss.service;

import com.hgx.apiboss.remote.ServiceDriverUserClient;
import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apiboss.service
 * @Author huogaoxu
 * @Date 2023-05-31 22:44
 * @Version 1.0
 **/
@Service
public class DriverCarService {
    @Resource
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCarUser(Car car){
        serviceDriverUserClient.addCarUser(car);

        return ResponseResult.success("");
    }
}
