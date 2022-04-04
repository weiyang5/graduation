package com.graduation.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.graduation.dto.UserDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

import static com.graduation.util.RedisConstants.LOGIN_TOKEN;
import static com.graduation.util.RedisConstants.LOGIN_TOKEN_TTL;
import static com.graduation.util.Status.TOKEN_ERROR;

public class ReflushTokenInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new TokenException(TOKEN_ERROR.getMsg());
        }
        Object user = redisTemplate.opsForValue().get(token);
        if(user==null){
            throw new TokenException(TOKEN_ERROR.getMsg());
        }
        UserDTO userDTO = BeanUtil.toBean(user, UserDTO.class);
        UserHolder.saveUser(userDTO);
        redisTemplate.expire(token,LOGIN_TOKEN_TTL, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
