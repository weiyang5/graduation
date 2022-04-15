package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Certificate;
import com.graduation.service.CertificateService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;
    @PostMapping("create")
    public Result create(@RequestBody Certificate certificate){
        boolean b = certificateService.save(certificate);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = certificateService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Certificate intention){
        boolean update = certificateService.updateById(intention);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(certificateService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page<Certificate> page=new Page<>(page1,10);
        LambdaQueryWrapper<Certificate> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Certificate::getName,jsonString.get("name"));
        }
        certificateService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        return Result.ok(page);
    }
}
