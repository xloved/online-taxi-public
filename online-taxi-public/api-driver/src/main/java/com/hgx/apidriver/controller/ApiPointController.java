package com.hgx.apidriver.controller;

import com.hgx.apidriver.service.ApiPointService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ApiDriverPointRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apidriver.controller
 * @Author huogaoxu
 * @Date 2023-06-06 13:56
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiPointController {

    @Resource
    private ApiPointService apiPointService;
    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){

        return apiPointService.upload(apiDriverPointRequest);
    }

}
