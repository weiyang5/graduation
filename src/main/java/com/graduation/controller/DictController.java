package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Dict;
import com.graduation.entity.User;
import com.graduation.service.DictService;
import com.graduation.util.RequiresRoles;
import com.graduation.util.Result;
import com.graduation.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;
    @PostMapping("create")
    public Result create(@RequestBody Dict dict){
        boolean b = dictService.save(dict);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = dictService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Dict dict){
        boolean update = dictService.updateById(dict);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(dictService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }

        Page<Dict> page=new Page<>(page1,10);
        LambdaQueryWrapper<Dict> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(jsonString.get("typeId")!=null){
            lambdaQueryWrapper.eq(Dict::getTypeId,jsonString.get("typeId"));
        }
        dictService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
}
