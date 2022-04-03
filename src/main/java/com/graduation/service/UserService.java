package com.graduation.service;

import com.graduation.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author a904497725
* @description 针对表【tb_user】的数据库操作Service
* @createDate 2022-03-31 21:53:59
*/
public interface UserService extends IService<User> {

    public User login(String UserName,String password);
}
