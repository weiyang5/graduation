package com.graduation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.entity.Company;
import com.graduation.service.CompanyService;
import com.graduation.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author a904497725
* @description 针对表【tb_company】的数据库操作Service实现
* @createDate 2022-03-31 21:53:59
*/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
    implements CompanyService{

    @Resource
    private CompanyMapper companyMapper;
    @Override
    public Company login(String account, String password) {
        Company company=new Company();
        company.setAccount(account);
        company.setPassword(password);
        LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.setEntity(company);
        Company company1 = companyMapper.selectOne(lambdaQueryWrapper);
        return company1;
    }
}




