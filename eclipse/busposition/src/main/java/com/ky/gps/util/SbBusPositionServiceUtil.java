package com.ky.gps.util;

import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.service.SbBusPositionService;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Daye
 * 获取sbBusPositionService并调用其方法的工具类
 */
@Component
public class SbBusPositionServiceUtil {

    @Resource
    private SbBusPositionService sbBusPositionService;
    private static SbBusPositionServiceUtil sbBusPositionServiceUtil;

    @PostConstruct
    public void init(){
        sbBusPositionServiceUtil = this;
        sbBusPositionServiceUtil.sbBusPositionService = this.sbBusPositionService;
    }
    
    public static void savePosition(SbBusPosition sbBusPosition){
        sbBusPositionServiceUtil.sbBusPositionService.savePosition(sbBusPosition);
    }
}
