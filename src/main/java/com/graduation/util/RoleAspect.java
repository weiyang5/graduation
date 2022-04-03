package com.graduation.util;

import com.graduation.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static com.graduation.util.RedisConstants.LOGIN_TOKEN;
import static com.graduation.util.Status.NO_AUTH;

@Component
@Aspect
public class RoleAspect {

    @Resource
    private RedisTemplate redisTemplate;
    @Pointcut
    public void pointCut(){

    }

    @Before("execution(* com.graduation.controller.*Controller.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method=signature.getMethod();
        //通过方法获取注解
        RequiresRoles annotation = method.getAnnotation(RequiresRoles.class);
        if (annotation!=null) {
            String token = UserHolder.getUser().getToken();
            UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get(token);
            if(userDTO!=null){
                if(!String.valueOf(userDTO.getType()).equals(String.valueOf(annotation.type().getCode()))){
                    throw new AuthException(Status.NO_AUTH.getMsg());
                }
            }else{
                throw new AuthException(NO_AUTH.getMsg());
            }
        }
    }
}
