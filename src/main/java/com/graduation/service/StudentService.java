package com.graduation.service;

import com.graduation.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author a904497725
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2022-03-31 21:53:59
*/
public interface StudentService extends IService<Student> {

    Student login(String account, String password);

}
