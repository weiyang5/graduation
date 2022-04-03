package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Post;
import com.graduation.service.PostService;
import com.graduation.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_post】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




