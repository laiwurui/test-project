package org.pedia.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.pedia.core.annotation.EnableCommonConfig;
import org.pedia.core.utils.ApplicationUtil;
import org.pedia.core.vo.Result;
import org.pedia.system.user.controller.UserController;
import org.pedia.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class TestSystemApplication {

    @Autowired
    private UserController controller;

    @Test
    public void testUser() {
        Result<User> view = controller.view("2bc9opnm91b35");
        log.info("userList:{}", view);
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("abc", 123456);
        log.info("redis value: {}", redisTemplate.opsForValue().get("abc"));
    }
}
