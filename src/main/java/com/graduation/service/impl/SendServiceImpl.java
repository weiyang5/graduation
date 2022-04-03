package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Send;
import com.graduation.service.SendService;
import com.graduation.mapper.SendMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_send】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class SendServiceImpl extends ServiceImpl<SendMapper, Send>
    implements SendService{

}




