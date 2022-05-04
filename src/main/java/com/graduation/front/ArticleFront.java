package com.graduation.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Article;
import com.graduation.service.ArticleService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/front/article")
public class ArticleFront {
    @Autowired
    private ArticleService articleService;
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

        Page<Article> page=new Page<>(page1,5);
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(jsonString.get("channelId")!=null){
            lambdaQueryWrapper.eq(Article::getChannelId,jsonString.get("channelId"));
        }
        articleService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }

}
