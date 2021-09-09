package com.example.demo.service.imp;

import com.example.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService{
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void put(String key, String value) {

        redisTemplate.opsForValue().set(key, value);
    }
}
