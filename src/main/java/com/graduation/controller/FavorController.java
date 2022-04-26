package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Company;
import com.graduation.entity.Favor;
import com.graduation.entity.Intention;
import com.graduation.service.CompanyService;
import com.graduation.service.FavorService;
import com.graduation.service.PostService;
import com.graduation.service.StudentService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/favor")
public class FavorController {
    @Autowired
    private FavorService favorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PostService postService;
    @PostMapping("create")
    public Result create(@RequestBody Map<String,Integer> map){
        UserDTO user = UserHolder.getUser();
        //1、判断用户是否登录
        if(user == null){
            return Result.ok("未登录不能操作");
        }
        Integer postId = map.get("postId");
        Integer companyId = map.get("companyId");

        //2、判断是否重复收藏
        Favor favorParam = new Favor();
        favorParam.setStudentId(user.getId());
        favorParam.setPostId(postId);
        favorParam.setCompanyId(companyId);
        LambdaQueryWrapper<Favor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.setEntity(favorParam);
        Favor favor1 = favorService.getBaseMapper().selectOne(lambdaQueryWrapper);
        if(favor1!=null){
            return Result.fail("请勿重复收藏");
        }

        Favor favor = new Favor();
        favor.setPostId(postId);
        favor.setCompanyId(companyId);
        favor.setFavorDate(new Date());
        favor.setStudentId(user.getId());
        //3、收藏
        boolean save = favorService.save(favor);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = favorService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer id = UserHolder.getUser().getId();
        List<Map<String,Object>> list=new ArrayList<>();
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        LambdaQueryWrapper<Favor> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favor::getStudentId,id);
        List<Favor> favors = favorService.getBaseMapper().selectList(lambdaQueryWrapper);
        favors.forEach(item->{
            Map<String,Object> map=new HashMap<>();
            map.put("id",item.getId());
            String companyName = companyService.getById(item.getCompanyId()).getName();
            map.put("companyName",companyName);
            map.put("postName",postService.getById(item.getPostId()).getName());
            map.put("favorDate",item.getFavorDate());
            list.add(map);
        });
        Page page=new Page(page1,10);
        page.setRecords(list);
        page.setTotal(list.size());
        return Result.ok(page);
    }

}
