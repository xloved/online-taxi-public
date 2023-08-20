package com.hgx.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public  JwtInterceptor jwtInterceptor(){//因为拦截器的执行是在bean初始化之前，所以先把拦截器注入到bean中进行初始化，验证token
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(jwtInterceptor())
               .addPathPatterns("/**")//拦截所有路径
               .excludePathPatterns("/noauthTest")//不拦截的路径
               .excludePathPatterns("/verification-code")
               .excludePathPatterns("/verification-code-check")
               .excludePathPatterns("/token-refresh")
               .excludePathPatterns("/token-refresh")
               .excludePathPatterns("/test-real-time-order/**");
    }
}
