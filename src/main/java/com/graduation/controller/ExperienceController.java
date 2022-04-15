package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Experience;
import com.graduation.service.ExperienceService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;
    @PostMapping("create")
    public Result create(@RequestBody Experience experience){
        boolean b = experienceService.save(experience);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = experienceService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Experience intention){
        boolean update = experienceService.updateById(intention);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(experienceService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page<Experience> page=new Page<>(page1,10);
        LambdaQueryWrapper<Experience> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("company")!=null){
            lambdaQueryWrapper.like(Experience::getCompany,jsonString.get("company"));
        }
        experienceService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        return Result.ok(page);
    }
}
