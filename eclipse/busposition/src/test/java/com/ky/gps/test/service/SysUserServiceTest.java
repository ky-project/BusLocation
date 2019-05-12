package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SysUserService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SysUserServiceTest {
    private ApplicationContext applicationContext;
    private SysUserService sysUserService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sysUserService = applicationContext.getBean(SysUserService.class);
    }

    @Test
    public void test02(){
        ResultWrapper userList = sysUserService.findUserList();
        System.out.println(userList);
        if(null == userList.getData()){
            System.out.println("error");
        } else{
            System.out.println("yes");
        }
    }

    @Test
    public void test01(){
        Map<String, Object> map = new HashMap<>(16);
        map.put("userName", "admin");
        map.put("password", "admin");
        Map<String, Object> baseInfo = sysUserService.simpleUserLogin(map);
        System.out.println(baseInfo);

    }

}

