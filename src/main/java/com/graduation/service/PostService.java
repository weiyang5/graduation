package com.graduation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
* @author a904497725
* @description 针对表【tb_post】的数据库操作Service
* @createDate 2022-03-31 21:53:59
*/
public interface PostService extends IService<Post> {
    public List<HashMap> getPostList(HashMap map);

}
