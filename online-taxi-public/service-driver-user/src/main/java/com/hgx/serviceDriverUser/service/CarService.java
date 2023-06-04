package com.hgx.serviceDriverUser.service;

import com.hgx.internalcomm.dto.Car;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.mapper.CarMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huogaoxu
 * @since 2023-05-30
 */
@Service
public class CarService  {

    @Resource
    CarMapper carMapper;

    public ResponseResult addCar(Car car){
        Date date = new Date();
        car.setGmtCreate(date);
        car.setGmtModified(date);
        carMapper.insert(car);
        return ResponseResult.success("");
    }


}
