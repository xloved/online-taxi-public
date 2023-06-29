package com.hgx.servicemap.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.dto.DicDistrict;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicemap.mapper.DicdistrictMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 国家地区controller
 * @Author huogaoxu
 * @Date 2023-01-02 15:56
 **/
@RestController
public class TestController {

    @Resource
    private DicdistrictMapper dicdistrictMapper;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/testmap")
    public String testmap(){
        Map<String, Object> map = new HashMap<>();
        map.put("address_code", "11000");
        List<DicDistrict> dicDistricts = dicdistrictMapper.selectByMap(map);
        System.out.println(dicDistricts);
        return "service-map";
    }


    @PostMapping("/finderCountry")
    public ResponseResult getFinder(@RequestBody DicDistrict dicDistrict){

        QueryWrapper<DicDistrict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_address_code",dicDistrict.getParentAddressCode());
        queryWrapper.eq("level",dicDistrict.getLevel());
        List<DicDistrict> dicDistricts = dicdistrictMapper.selectList(queryWrapper);
        System.out.println("查询结果"+dicDistricts);
        return ResponseResult.success(dicDistricts);
    }
}
