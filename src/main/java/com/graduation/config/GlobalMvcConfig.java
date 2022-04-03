package com.graduation.config;

import com.graduation.util.LoginInterceptor;
import com.graduation.util.ReflushTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class GlobalMvcConfig implements WebMvcConfigurer {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/login"
                ).order(1);
        registry.addInterceptor(new ReflushTokenInterceptor(redisTemplate)).order(0);
    }
}
