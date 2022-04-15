package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Dict;
import com.graduation.entity.Student;
import com.graduation.service.StudentService;
import com.graduation.util.RequiresRoles;
import com.graduation.util.Result;
import com.graduation.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("create")
    public Result create(@RequestBody Student student){
        boolean b = studentService.save(student);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = studentService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Student student){
        boolean update = studentService.updateById(student);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(studentService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }

        Page<Student> page=new Page<>(page1,10);
        LambdaQueryWrapper<Student> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Student::getName,jsonString.get("name"));
        }
        if(jsonString.get("college")!=null){
            lambdaQueryWrapper.like(Student::getCollege,jsonString.get("college"));
        }
        studentService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
}
