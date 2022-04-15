package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Train;
import com.graduation.service.TrainService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainService trainService;
    @PostMapping("create")
    public Result create(@RequestBody Train train){
        boolean b = trainService.save(train);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = trainService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Train intention){
        boolean update = trainService.updateById(intention);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(trainService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page<Train> page=new Page<>(page1,10);
        LambdaQueryWrapper<Train> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("course")!=null){
            lambdaQueryWrapper.like(Train::getCourse,jsonString.get("course"));
        }
        trainService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        return Result.ok(page);
    }
}
