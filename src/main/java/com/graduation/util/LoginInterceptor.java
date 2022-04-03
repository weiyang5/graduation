package com.graduation.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //是否需要拦截（ThreadLocal里是否有用户）
        if (UserHolder.getUser()==null) {
            response.setStatus(401);
            return false;
        }
        return true;
    }

}
