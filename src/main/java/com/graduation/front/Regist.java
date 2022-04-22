package com.graduation.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graduation.entity.Company;
import com.graduation.entity.Student;
import com.graduation.service.CompanyService;
import com.graduation.service.StudentService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/front/regist")
public class Regist {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;
    @PostMapping("student_create")
    public Result student_create(@RequestBody Student student){

        Student param = new Student();
        param.setAccount(student.getAccount());
        LambdaQueryWrapper<Student> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.setEntity(param);
        Student student1 = studentService.getBaseMapper().selectOne(lambdaQueryWrapper);
        if(student1!=null){
            return Result.fail("注册失败，账号因存在！");
        }else {
            boolean flag = studentService.save(student);

            if(flag){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }
    }

    @PostMapping("company_create")
    public Result student_create(@RequestBody Company company){
        Company param = new Company();
        param.setAccount(company.getAccount());
        LambdaQueryWrapper<Company> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.setEntity(param);
        Company company1 = companyService.getBaseMapper().selectOne(lambdaQueryWrapper);
        if(company1!=null){
            return Result.fail("注册失败，账号因存在！");
        }else {
            boolean flag = companyService.save(company);
            if(flag){
                return Result.ok();
            }else{
                return Result.fail();
            }
        }
    }
}
