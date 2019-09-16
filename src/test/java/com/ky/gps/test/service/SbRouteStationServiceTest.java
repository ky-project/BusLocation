package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.util.JudgeTimeUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

public class SbRouteStationServiceTest {
    private ApplicationContext applicationContext;
    private SbRouteStationService sbRouteStationService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbRouteStationService = applicationContext.getBean(SbRouteStationService.class);
    }

    @Test
    public void test01(){
        long l = System.currentTimeMillis();
        ResultWrapper allRouteStation = sbRouteStationService.findAllRouteStation();
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void test02(){
        long l = System.currentTimeMillis();
        ResultWrapper allRouteStation = sbRouteStationService.findRealTimeAllRouteStation(JudgeTimeUtil.getWeek(), String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
        Object data = allRouteStation.getData();
        System.out.println(data);
        System.out.println(System.currentTimeMillis() - l);
    }
}
