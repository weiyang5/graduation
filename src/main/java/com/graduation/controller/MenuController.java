package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Menu;
import com.graduation.mapper.MenuMapper;
import com.graduation.service.MenuService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @PostMapping("query")
    public Result query(){
        UserDTO userDTO = UserHolder.getUser();
        if(userDTO==null){
            return Result.fail("userDTO为空");
        }
        List<Menu> menus = menuService.query(userDTO.getType());
        return Result.ok(menus);
    }
}
