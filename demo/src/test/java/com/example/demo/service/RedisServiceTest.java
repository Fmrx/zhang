package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Resource
    private RedisService redisService;

    @Test
    public void set() {
        redisService.set("zhang", "1");
    }

    @Test
    public void get() {
    }

    @Test
    public void exists() {
    }
}
