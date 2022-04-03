package com.graduation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.entity.User;
import com.graduation.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MybatisPlusPluginsTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void test(){
        Page<User> page=new Page<>(1,10);

        userMapper.selectPage(page,null);
        System.out.println(page.getTotal());

    }

}
