package com.hgx.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description com.hgx.serviceorder
 * @Author huogaoxu
 * @Date 2023-06-12 10:54
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.hgx.serviceorder.mapper")
@EnableFeignClients
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
