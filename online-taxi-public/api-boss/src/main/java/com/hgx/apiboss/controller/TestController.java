package com.hgx.apiboss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description com.hgx.apiboss.controller
 * @Author huogaoxu
 * @Date 2023-05-28 22:45
 * @Version 1.0
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "api-boss";
    }
}
