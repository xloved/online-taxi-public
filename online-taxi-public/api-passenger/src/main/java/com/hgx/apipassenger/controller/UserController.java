package com.hgx.apipassenger.controller;

import com.hgx.apipassenger.service.UserService;
import com.hgx.internalcomm.dto.ResponseResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getByUser(HttpServletRequest request){

        String accessToken=request.getHeader("Authorization");

       return userService.getUserByAccessToken(accessToken);
    }
}
