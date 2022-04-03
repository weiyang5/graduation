package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Article;
import com.graduation.service.ArticleService;
import com.graduation.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_article】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




