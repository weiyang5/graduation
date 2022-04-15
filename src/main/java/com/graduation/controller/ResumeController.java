package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Resume;
import com.graduation.service.ResumeService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @PostMapping("create")
    public Result create(@RequestBody Resume resume){
        UserDTO user = UserHolder.getUser();
        Integer id = user.getId();
        resume.setStudentId(id);
        boolean b = resumeService.save(resume);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = resumeService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Resume company){
        boolean update = resumeService.updateById(company);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("info")
    public Result info(){
        UserDTO user = UserHolder.getUser();
        return  Result.ok(resumeService.getById(user.getId()));
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(resumeService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        UserDTO user = UserHolder.getUser();
        Integer id = user.getId();
        Page<Resume> page=new Page<>(page1,10);
        LambdaQueryWrapper<Resume> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Resume::getStudentId,id);

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Resume::getName,jsonString.get("name"));
        }
        resumeService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
}
