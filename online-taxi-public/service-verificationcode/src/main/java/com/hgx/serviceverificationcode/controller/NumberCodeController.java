package com.hgx.serviceverificationcode.controller;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取数字验证码
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        System.out.println("验证码为"+size);
        double mathRandom = (Math.random()*9+1) * (Math.pow(10,size - 1));
        System.out.println(mathRandom);
        int resultInt = (int)mathRandom;
        System.out.println("generator src code:"+resultInt);

        //定义返回值，返回验证码
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);
        return ResponseResult.success(response);

    }
}
