package com.ky.gps.util;

import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.service.inter.SbBusPositionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SbBusPositionServiceUtil {
    private static ApplicationContext applicationContext;
    private static SbBusPositionService sbBusPositionService;

    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbBusPositionService = applicationContext.getBean(SbBusPositionService.class);
    }
    
    public static void save(SbBusPosition sbBusPosition){
        sbBusPositionService.savePosition(sbBusPosition);
    }
}
