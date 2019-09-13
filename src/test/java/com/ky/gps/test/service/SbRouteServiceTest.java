package com.ky.gps.test.service;

import com.ky.gps.service.SbRouteService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class SbRouteServiceTest {

    private ApplicationContext applicationContext;
    private SbRouteService sbRouteService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbRouteService = applicationContext.getBean(SbRouteService.class);
    }

    @Test
    public void findAllBaseInfoTest01(){
        List<Map<String, Object>> baseInfoList = sbRouteService.findAllBaseInfo();
    }
}
