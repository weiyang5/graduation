package com.graduation.service;

import com.graduation.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author a904497725
* @description 针对表【tb_menu】的数据库操作Service
* @createDate 2022-03-31 21:53:59
*/
public interface MenuService extends IService<Menu> {
    public List<Menu> query(Integer type);
}
