package com.graduation.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Post;
import com.graduation.mapper.PostMapper;
import com.graduation.service.PostService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/post")
public class PostFront {
    @Autowired
    PostService postService;
    @Resource
    PostMapper postMapper;
    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        Page page=new Page(page1,10);
        LambdaQueryWrapper<Post> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Post::getName,jsonString.get("name"));
        }
//        postService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        Page<Map<String,String>> postPage = postMapper.frontGetData(lambdaQueryWrapper, page);
        List records = postPage.getRecords();
        records.forEach(System.out::println);
        return Result.ok(page);
    }
}
