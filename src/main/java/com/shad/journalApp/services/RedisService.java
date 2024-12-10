package com.shad.journalApp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shad.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object o,Long ttL) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o);
           redisTemplate.opsForValue().set(key, jsonValue,ttL, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception ", e.getMessage());

        }

    }

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
           return mapper.readValue(o.toString(), entityClass);

        } catch (Exception e) {
            log.error("Exception ", e.getMessage());
            return null;
        }

    }

}
