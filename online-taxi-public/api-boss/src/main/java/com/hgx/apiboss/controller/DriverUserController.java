package com.hgx.apiboss.controller;

import com.hgx.apiboss.service.DriverCarService;
import com.hgx.apiboss.service.DriverUserService;
import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.DriverUser;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 司机用户
 * @Author huogaoxu
 * @Date 2023-05-29 11:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class DriverUserController {

    @Resource
    private DriverUserService driverUserService;
    @Resource
    private DriverCarService driverCarService;

    /**
     * 添加司机
     * @param driverUser
     * @return
     */
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        /**
         * 也可以使用这种方式，当添加司机信息的时候，如果司机ID为NULL则插入司机信息，如果不为NULL进行修改司机信息
         * if(driverUser.getId() == null){
         *             driverUserService.addDriverUser(driverUser);
         *         }
         *         return driverUserService.updateDriverUser(driverUser);
         */
        return driverUserService.addDriverUser(driverUser);
    }

    /**
     * 修改司机信息
     * @param driverUser
     * @return
     */
    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser){

        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 添加车辆信息
     * @param car
     * @return
     */
    @PostMapping("/car")
    public ResponseResult addCarUser(@RequestBody Car car){

        return driverCarService.addCarUser(car);
    }
}
