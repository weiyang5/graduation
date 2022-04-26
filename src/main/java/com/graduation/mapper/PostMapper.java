package com.graduation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author a904497725
* @description 针对表【tb_post】的数据库操作Mapper
* @createDate 2022-03-31 21:53:59
* @Entity com.graduation.entity.Post
*/
public interface PostMapper extends BaseMapper<Post> {
    public List<HashMap> getPostList(HashMap map);
    @Select("<script>"+
            "select tb_post.* ,tb_company.id companyId,tb_company.name companyName,size,type from tb_post,tb_company where tb_post.company_id = tb_company.id"+
            "</script>")
    Page<Map<String,String>> frontGetData(@Param("ew") LambdaQueryWrapper ew, Page page);
}




