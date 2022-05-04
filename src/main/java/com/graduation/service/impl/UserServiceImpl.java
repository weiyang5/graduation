package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.User;
import com.graduation.service.UserService;
import com.graduation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author a904497725
* @description 针对表【tb_user】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String userName, String password) {
        User query=new User();
        query.setUserName(userName);
        query.setPassword(password);
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.setEntity(query);

        User user = userMapper.selectOne(lambdaQueryWrapper);
        return user;
    }
}




