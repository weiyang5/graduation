package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Student;
import com.graduation.service.StudentService;
import com.graduation.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author a904497725
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student login(String account, String password) {
        Student student=new Student();
        student.setAccount(account);
        student.setPassword(password);
        LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.setEntity(student);
        Student student1 = studentMapper.selectOne(lambdaQueryWrapper);
        return student1;
    }
}




