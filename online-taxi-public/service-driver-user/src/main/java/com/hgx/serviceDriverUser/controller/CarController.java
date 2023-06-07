package com.hgx.serviceDriverUser.controller;


import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huogaoxu
 * @since 2023-05-30
 */
@RestController
@RequestMapping("/user")
public class CarController {

    @Resource
    private CarService carService;

    /**
     * 添加车辆
     * @param car
     * @return
     */
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        return carService.addCar(car);
    }

    /**、
     * 根据车辆ID查询车辆信息
     * @param carId
     * @return
     */
    @GetMapping("/getCar")
    public ResponseResult<Car> getCarById(Long carId){

        return carService.getCarById(carId);
    }

}
