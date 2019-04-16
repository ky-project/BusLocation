package com.ky.gps.controller;

import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.entity.SbGps;
import com.ky.gps.service.inter.SbBusPositionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Daye
 * 模拟GPS信号，以api的形式进行获取并存入数据库
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/position")
public class GainPositionHandler {

    @Resource
    private SbBusPositionService sbBusPositionService;

    /**
     * 获取json格式的sbGps定位数据
     * @param sbBusPosition 定位数据存放的类
     */
    @RequestMapping(value = "/gain")
    public void gainPosition(@RequestBody SbBusPosition sbBusPosition){
        System.out.println(sbBusPosition);
        sbBusPosition.setSbGps(new SbGps("20190304"));
        sbBusPosition.setCreatedBy("test");
        sbBusPosition.setUpdatedBy("test");
        sbBusPositionService.savePosition(sbBusPosition);
    }
}
