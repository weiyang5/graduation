package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Menu;
import com.graduation.service.MenuService;
import com.graduation.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author a904497725
* @description 针对表【tb_menu】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<Menu> query(Integer type) {
        LambdaQueryWrapper<Menu> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Menu::getType,type);
        List<Menu> menus = getBaseMapper().selectList(lambdaQueryWrapper);
        return menus;
    }
}




