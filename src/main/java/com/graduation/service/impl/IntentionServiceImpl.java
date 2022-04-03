package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Intention;
import com.graduation.service.IntentionService;
import com.graduation.mapper.IntentionMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_intention】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class IntentionServiceImpl extends ServiceImpl<IntentionMapper, Intention>
    implements IntentionService{

}




