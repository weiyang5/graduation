package com.graduation.controller;

import cn.hutool.core.lang.UUID;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Company;
import com.graduation.entity.Student;
import com.graduation.entity.User;
import com.graduation.service.CompanyService;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import com.graduation.util.Result;
import com.graduation.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.graduation.util.RedisConstants.LOGIN_TOKEN;
import static com.graduation.util.RedisConstants.LOGIN_TOKEN_TTL;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StudentService studentService;
    @Resource
    private RedisTemplate<Serializable,Serializable> redisTemplate;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String > map){
        String account = map.get("account");
        String password = map.get("password");
        String type = map.get("type");
        String msg= "用户名或密码错误";
        boolean flag = false;
        UserDTO userDTO=new UserDTO();
        if(String.valueOf(Role.ADMIN.getCode()).equals(type)){
            User login = userService.login(account, password);
            if (login!=null){
                userDTO.setId(login.getId());
                userDTO.setAccount(login.getUserName());
                userDTO.setName(login.getName());
                userDTO.setType(Role.ADMIN.getCode());
                flag=true;
            }
        }
        if(String.valueOf(Role.COMPANY.getCode()).equals(type)){
            Company login = companyService.login(account, password);
            if(login != null){
                if(login.getStatus() ==0){
                    msg = "账号审批中，请等待";
                }else if(login.getStatus() ==2){
                    msg = "审批未通过，请与管理员沟通";
                }else  {
                    userDTO.setId(login.getId());
                    userDTO.setAccount(login.getAccount());
                    userDTO.setName(login.getName());
                    userDTO.setType(Role.COMPANY.getCode());
                    flag = true;
                }
            }
        }
        if(String.valueOf(Role.STUDENT.getCode()).equals(type)){
            Student login = studentService.login(account, password);
            if (login!=null){
                userDTO.setId(login.getId());
                userDTO.setAccount(login.getAccount());
                userDTO.setName(login.getName());
                userDTO.setType(Role.STUDENT.getCode());
                flag=true;
            }
        }
        if(flag){
            String token= UUID.randomUUID().toString(true);
            userDTO.setToken(LOGIN_TOKEN+token);
            redisTemplate.opsForValue().set(LOGIN_TOKEN+token,userDTO,LOGIN_TOKEN_TTL, TimeUnit.SECONDS);

            return Result.ok(userDTO);
        }else{
            return Result.fail(msg);
        }

    }
}
