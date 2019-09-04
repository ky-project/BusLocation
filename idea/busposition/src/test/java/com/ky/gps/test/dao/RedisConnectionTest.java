package com.ky.gps.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisConnectionTest {

    private ClassPathXmlApplicationContext applicationContext;
    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
        redisTemplate = applicationContext.getBean(RedisTemplate.class);
    }

    @Test
    public void testSetKey(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("k1", "v1");
        System.out.println(valueOperations.get("k1"));
    }

    @Test
    public void testConnection(){
        System.out.println(redisTemplate.toString());
    }
}
