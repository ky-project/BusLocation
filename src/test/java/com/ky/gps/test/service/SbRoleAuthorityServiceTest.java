package com.ky.gps.test.service;

import com.ky.gps.service.SbRoleAuthorityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SbRoleAuthorityServiceTest {
    private ApplicationContext applicationContext;
    private SbRoleAuthorityService sbRoleAuthorityService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbRoleAuthorityService = applicationContext.getBean(SbRoleAuthorityService.class);
    }

    @Test
    public void test01(){
        List<String> roles = new ArrayList<>();
        roles.add("super");
        List<String> list = sbRoleAuthorityService.findSaNameBySrSource(roles);
        System.out.println(list);
    }

}
