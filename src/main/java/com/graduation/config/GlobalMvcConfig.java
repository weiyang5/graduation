package com.graduation.config;

import com.graduation.util.ReflushTokenInterceptor;
import com.graduation.util.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class GlobalMvcConfig implements WebMvcConfigurer {
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private UploadConfig config;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReflushTokenInterceptor(redisTemplate))
                .excludePathPatterns(
                        "/login",
                        "/front/**",
                        config.getAccessPath()
                );
    }
    //使用CorsFilter解决跨域问题
    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域请求的域名
        corsConfiguration.addAllowedOrigin("*");
        //允许跨域请求方法
        corsConfiguration.addAllowedMethod("*");
        //允许任何头部
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        return corsFilter;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(config.getAccessPath())
                .addResourceLocations("file:"+config.getUploadFolder());
    }
}
