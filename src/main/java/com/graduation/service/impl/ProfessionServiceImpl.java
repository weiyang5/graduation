package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Profession;
import com.graduation.service.ProfessionService;
import com.graduation.mapper.ProfessionMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_profession】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class ProfessionServiceImpl extends ServiceImpl<ProfessionMapper, Profession>
    implements ProfessionService{

}




