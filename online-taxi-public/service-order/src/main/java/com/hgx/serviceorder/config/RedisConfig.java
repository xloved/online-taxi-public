package com.hgx.serviceorder.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description 添加redisson相关配置
 * @Author huogaoxu
 * @Date 2023-08-20 17:46
 * @Version 1.0
 **/
@Component
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.70.130:6379").setDatabase(0);

        return Redisson.create(config);

    }
}
