package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Article;
import com.graduation.service.ArticleService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ArticleService articleService;
    @PostMapping("create")
    public Result create(@RequestBody Article article){
        UserDTO userDTO = UserHolder.getUser();
        article.setCreateDate(new Date());
        article.setCreateUser(userDTO.getId());
        boolean b = articleService.save(article);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = articleService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Article article){
        boolean update = articleService.updateById(article);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(articleService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }

        Page<Article> page=new Page<>(page1,10);
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(jsonString.get("title")!=null){
            lambdaQueryWrapper.like(Article::getTitle,jsonString.get("title"));
        }
        articleService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
}
