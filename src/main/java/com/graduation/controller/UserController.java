package com.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Type;
import com.graduation.entity.User;
import com.graduation.service.UserService;
import com.graduation.util.RequiresRoles;
import com.graduation.util.Result;
import com.graduation.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("create")
    @RequiresRoles(type = Role.ADMIN)
    public Result create(@RequestBody User user){
        boolean b = userService.save(user);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = userService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody User user){
        boolean update = userService.updateById(user);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(userService.getById(id));
    }

    @PostMapping("query")
    @RequiresRoles(type = Role.ADMIN)
    public Map<String,Object> query(@RequestBody  User user){
        Page<User> page=new Page<>(1,10);
        userService.query().page(page);
        return Result.ok(page);
    }
}
