package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Intention;
import com.graduation.entity.Resume;
import com.graduation.service.IntentionService;
import com.graduation.service.ResumeService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/intention")
public class IntentionController {
    @Autowired
    private IntentionService intentionService;

    @Autowired
    private ResumeService resumeService;
    @PostMapping("create")
    public Result create(@RequestBody Intention intention){
        boolean b = intentionService.save(intention);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = intentionService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Intention intention){
        boolean update = intentionService.updateById(intention);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(intentionService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page<Intention> page=new Page<>(page1,10);
        LambdaQueryWrapper<Intention> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Intention::getName,jsonString.get("name"));
        }
        intentionService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        return Result.ok(page);
    }
}
