package com.hgx.apiboss.service;

import com.hgx.apiboss.remote.ServiceDriverUserClient;
import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description api-boss 调用service-driver-user 维护车辆与司机关系
 * @Author huogaoxu
 * @Date 2023-06-02 20:55
 * @Version 1.0
 **/
@Service
public class DriverCarRelationshipService {

    @Resource
    ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addBind(DriverCarBindingRelationship details){
        serviceDriverUserClient.bindCar(details);
        return ResponseResult.success("");
    }

    public ResponseResult unBind(DriverCarBindingRelationship details){
        serviceDriverUserClient.unbindCar(details);
        return ResponseResult.success("");
    }
}
