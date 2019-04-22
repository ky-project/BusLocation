package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.entity.SbGps;
import com.ky.gps.service.inter.SbBusPositionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SbBusPositionServiceTest {
    private ApplicationContext applicationContext;
    private SbBusPositionService sbBusPositionService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbBusPositionService = applicationContext.getBean(SbBusPositionService.class);
    }

    @Test
    public void test03(){
        ResultWrapper allEffectiveRoutePosition = sbBusPositionService.findAllEffectiveRoutePosition();
    }

    @Test
    public void test02(){
        List<Map<String, Object>> maps = sbBusPositionService.findAllPositionByBusId("20180401");

        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    @Test
    public void test01(){
        //new一个对象，待存入数据库
        SbBusPosition sbBusPosition = new SbBusPosition();
        //set一个SbGPS对象，需给定它的id
//        sbBusPosition.setSbBus(new SbBus(1));
        sbBusPosition.setSbGps(new SbGps("20190421"));
        //setGPS信号的时间
        sbBusPosition.setSbpRecodeTime(Timestamp.valueOf("2019-04-14 14:45:35"));
        //set经度
        sbBusPosition.setSbpLongitude(99.337);
        //set纬度
        sbBusPosition.setSbpLatitude(42.5228);
        //set速度
        sbBusPosition.setSbpVelocity(77.32);
        //set方向角
        sbBusPosition.setSbpDirection(55.7);
        //保存该对象
        sbBusPositionService.savePosition(sbBusPosition);
    }


}
