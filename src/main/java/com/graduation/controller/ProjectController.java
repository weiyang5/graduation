package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Project;
import com.graduation.service.ProjectService;
import com.graduation.util.Result;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("create")
    public Result create(@RequestBody Project project){
        boolean b = projectService.save(project);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = projectService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Project intention){
        boolean update = projectService.updateById(intention);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(projectService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page<Project> page=new Page<>(page1,10);
        LambdaQueryWrapper<Project> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Project::getName,jsonString.get("name"));
        }
        projectService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        return Result.ok(page);
    }
}
