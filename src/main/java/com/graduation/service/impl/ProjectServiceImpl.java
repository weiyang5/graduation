package com.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Project;
import com.graduation.service.ProjectService;
import com.graduation.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

/**
* @author a904497725
* @description 针对表【tb_project】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

}




