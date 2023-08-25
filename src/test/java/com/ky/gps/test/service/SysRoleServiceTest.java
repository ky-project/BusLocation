package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.service.SysRoleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysRoleServiceTest {
    private ApplicationContext applicationContext;
    private SysRoleService sysRoleService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sysRoleService = applicationContext.getBean(SysRoleService.class);
    }

    @Test
    public void test01(){
        ResultWrapper allRole = sysRoleService.findAllRole();
        System.out.println(allRole.getData());
    }
}
