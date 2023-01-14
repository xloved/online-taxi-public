package com.hgx.servicemap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-02 15:56
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
