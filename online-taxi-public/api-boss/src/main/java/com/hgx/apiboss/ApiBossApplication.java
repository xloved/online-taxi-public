package com.hgx.apiboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description com.hgx.apiboss
 * @Author huogaoxu
 * @Date 2023-05-28 22:44
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
public class ApiBossApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBossApplication.class, args);
    }
}
