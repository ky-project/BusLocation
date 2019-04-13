package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.inter.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SysUserServiceTest {
    private ApplicationContext applicationContext;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
    }

    @Test
    public void test01(){
        SysUserService sysUserService = applicationContext.getBean(SysUserService.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("userName", "admin");
        map.put("password", "admin");
        Map<String, Object> baseInfo = sysUserService.simpleUserLogin(map);
        System.out.println(baseInfo);

    }

}

