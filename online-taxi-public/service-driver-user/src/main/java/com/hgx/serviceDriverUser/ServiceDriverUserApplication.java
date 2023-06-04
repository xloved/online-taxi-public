package com.hgx.serviceDriverUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description com.hgx.serviceDriverUser
 * @Author huogaoxu
 * @Date 2023-05-28 20:33
 * @Version 1.0
 **/
@MapperScan("com.hgx.serviceDriverUser.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceDriverUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }
}
