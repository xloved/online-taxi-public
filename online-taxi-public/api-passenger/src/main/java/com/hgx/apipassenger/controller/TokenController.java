package com.hgx.apipassenger.controller;

import com.hgx.apipassenger.service.TokenService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TokenController {
    @Resource
    private TokenService tokenService;

    //刷新token
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("原来的token是："+refreshToken);

        return tokenService.refreshToken(refreshToken);
    }
}
