package com.hgx.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 司机端校验accessToken
 * @Author huogaoxu
 * @Date 2023-06-04 16:43
 * @Version 1.0
 **/
@RestController
public class TestController {

    /**
     * 需要授权的接口
     * @return
     */
    @GetMapping("/auth")
    public  String testAuthor(){
        return "author";
    }


    /**
     * 不需要授权的接口
     * @return
     */
    @GetMapping("/noauthTest")
    public  String testNoAuthor(){
        return "no author";
    }
}
