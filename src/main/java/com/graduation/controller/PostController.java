package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Post;
import com.graduation.entity.Resume;
import com.graduation.service.PostService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("create")
    public Result create(@RequestBody Post post){
        UserDTO user = UserHolder.getUser();
        Integer id = user.getId();
        post.setId(id);
        boolean b = postService.save(post);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = postService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Post company){
        boolean update = postService.updateById(company);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("info")
    public Result info(){
        UserDTO user = UserHolder.getUser();
        return  Result.ok(postService.getById(user.getId()));
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(postService.getById(id));
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
        Page<Post> page=new Page<>(page1,10);
        LambdaQueryWrapper<Post> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Post::getCompanyId,id);

        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Post::getName,jsonString.get("name"));
        }
        postService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
}
