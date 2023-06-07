package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.PointRequest;
import com.hgx.servicemap.service.PointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 轨迹上传与管理
 * @Author huogaoxu
 * @Date 2023-06-06 9:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/point")
public class PointController {

    @Resource
    private PointService pointService;

    /**
     * 轨迹点上传
     * @param pointRequest
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uplod(@RequestBody PointRequest pointRequest){

        return pointService.upload(pointRequest);
    }
}
