package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Train;
import com.graduation.service.TrainService;
import com.graduation.mapper.TrainMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_train】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train>
    implements TrainService{

}




