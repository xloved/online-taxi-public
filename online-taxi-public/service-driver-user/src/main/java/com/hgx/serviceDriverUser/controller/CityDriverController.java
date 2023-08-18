package com.hgx.serviceDriverUser.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceDriverUser.service.CityDriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 司机城市对应Controller
 * @Author huogaoxu
 * @Date 2023-06-30 15:44
 * @Version 1.0
 **/
@RestController
@RequestMapping("/city-driver")
public class CityDriverController {

    @Resource
    private CityDriverService cityDriverService;

    /**
     * 根据城市码查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @GetMapping("/is-alailable-driver")
    public ResponseResult isAvailableDriver(@RequestParam String cityCode){

        return cityDriverService.isAvailableDriver(cityCode);
    }
}
