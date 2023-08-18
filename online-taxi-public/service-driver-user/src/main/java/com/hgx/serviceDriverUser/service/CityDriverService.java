package com.hgx.serviceDriverUser.service;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.mapper.DriverUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.serviceDriverUser.service
 * @Author huogaoxu
 * @Date 2023-06-30 15:48
 * @Version 1.0
 **/
@Service
public class CityDriverService {


    @Resource
    DriverUserMapper driverUserMapper;


    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        int byCityCode = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if (byCityCode > 0 ) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.success(false);
        }

    }




}
