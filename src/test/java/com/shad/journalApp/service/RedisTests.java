package com.shad.journalApp.service;

import com.shad.journalApp.services.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testSendEmail() {
        redisTemplate.opsForValue().set("email", "test@email.com");
        Object name = redisTemplate.opsForValue().get("name");
        int a=1;
    }
}
