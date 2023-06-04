package com.hgx.apidriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description com.hgx.apidriver
 * @Author huogaoxu
 * @Date 2023-05-29 23:08
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class ApiDriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDriverApplication.class, args);
    }

    @GetMapping("/test")
    static String test(){
        return "api-driver";
    }
}
