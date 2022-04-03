package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Type;
import com.graduation.service.TypeService;
import com.graduation.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_type】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




