package com.hgx.serviceprice.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-10 23:28
 **/
public class TestController {

    @GetMapping(value = "/wiki/Reqs")
    public ResponseResult tetsReqs(@RequestParam(required = false) Integer type,
                                   @RequestParam(required = false) Integer timeout){
        Map<String, String> map = new HashMap<>();
        //map.putAll(testService.aswiki(type,timeout));
        //map.putAll(testService.kswiki(type,timeout));
        //.putAll(testService.dawiki(type,timeout));
        return ResponseResult.success();
    }
}
