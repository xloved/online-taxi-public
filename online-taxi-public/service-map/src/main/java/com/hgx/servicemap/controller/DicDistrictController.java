package com.hgx.servicemap.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicemap.service.DicDistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-31 23:05
 **/
@RestController
public class DicDistrictController {

    @Resource
    private DicDistrictService dicDistrictService;

    @GetMapping(value = "/dic-district")
    public ResponseResult initDicDistrict(@RequestParam("keywords") String keywords){


        return dicDistrictService.initDistrict(keywords);
    }
}
