package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TrackResponse;
import com.hgx.servicemap.service.TrackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 轨迹控制器
 * @Author huogaoxu
 * @Date 2023-06-05 20:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/track")
public class TrackController {

    @Resource
    private TrackService trackService;

    /**
     * 创建轨迹ID
     * @param tid 终端id
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<TrackResponse> addTrack(String tid){

        return trackService.addTrack(tid);
    }
}
