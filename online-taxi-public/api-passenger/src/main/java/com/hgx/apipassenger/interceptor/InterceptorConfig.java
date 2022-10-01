package com.hgx.apipassenger.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new JwtInterceptor())
               .addPathPatterns("/**")//拦截所有路径
               .excludePathPatterns("/noauthTest");//不拦截的路径
    }
}
