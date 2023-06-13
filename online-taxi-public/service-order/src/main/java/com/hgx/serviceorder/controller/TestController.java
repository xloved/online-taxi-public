package com.hgx.serviceorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description com.hgx.serviceorder.controller
 * @Author huogaoxu
 * @Date 2023-06-12 10:55
 * @Version 1.0
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-order";
    }
}
