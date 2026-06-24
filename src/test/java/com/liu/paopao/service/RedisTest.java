package com.liu.paopao.service;

import com.liu.paopao.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * Redis 测试
 *
 * 
 * 
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("liuString", "dog");
        valueOperations.set("liuInt", 1);
        valueOperations.set("liuDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("liu");
        valueOperations.set("liuUser", user);
        // 查
        Object liu = valueOperations.get("liuString");
        Assertions.assertTrue("dog".equals((String) liu));
        liu = valueOperations.get("liuInt");
        Assertions.assertTrue(1 == (Integer) liu);
        liu = valueOperations.get("liuDouble");
        Assertions.assertTrue(2.0 == (Double) liu);
        System.out.println(valueOperations.get("liuUser"));
        valueOperations.set("liuString", "dog");
        redisTemplate.delete("liuString");
    }
}
