package com.hgx.apiboss.controller;

import com.hgx.apiboss.service.DriverCarRelationshipService;
import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 司机与车辆关系
 * @Author huogaoxu
 * @Date 2023-06-02 20:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarRelationshipController {


    @Resource
    private DriverCarRelationshipService driverCarRelationship;
    @PostMapping("/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship details){

        return driverCarRelationship.addBind(details);

    }

    @PostMapping("/unbind")
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship details){

        return driverCarRelationship.unBind(details);
    }
}
