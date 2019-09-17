package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.service.SysAuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysAuthorityServiceTest {
    private ApplicationContext applicationContext;
    private SysAuthorityService sysAuthorityService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sysAuthorityService = applicationContext.getBean(SysAuthorityService.class);
    }

    @Test
    public void test01(){
        ResultWrapper all = sysAuthorityService.findAll();
        System.out.println(all.getData());
    }
}
