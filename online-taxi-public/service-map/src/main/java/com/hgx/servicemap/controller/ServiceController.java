package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicemap.service.ServiceFromMapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 高德地图猎鹰轨迹服务管理
 * @Author huogaoxu
 * @Date 2023-06-04 20:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/service")
public class ServiceController {


    @Resource
    private ServiceFromMapService serviceFromMapService;

    @PostMapping("/add")
    public ResponseResult add(String name){

        return serviceFromMapService.add(name);
    }
}
