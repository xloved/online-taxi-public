package com.hgx.serviceDriverUser.controller;


import com.hgx.internalcomm.dto.DriverCarBindingRelationship;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.service.DriverCarBindingRelationshipService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  司机与车辆关系
 * </p>
 *
 * @author huogaoxu
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {

    @Resource
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    /**
     * 司机与车辆进行绑定
     * @param descriptor
     * @return
     */
    @PostMapping("/bind")
    public ResponseResult bindCar(@RequestBody DriverCarBindingRelationship descriptor){

    return driverCarBindingRelationshipService.carBind(descriptor);
    }

    /**
     * 司机与车辆进行解绑
     * @param descriptor
     * @return
     */
    @PostMapping("/unbind")
    public ResponseResult unbindCar(@RequestBody DriverCarBindingRelationship descriptor) {

        return driverCarBindingRelationshipService.unbindCar(descriptor);
    }

}
