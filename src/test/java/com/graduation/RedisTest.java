package com.graduation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void test1(){
        String name = redisTemplate.opsForValue().get("login:token:6603bb39f5d54ef3b87a7667a1e31a50");
        System.out.println(name);
    }
}
