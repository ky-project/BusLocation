package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBusRoute;
import com.ky.gps.service.inter.*;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 根据路线查询校车实时位置
 */
@Controller
@RequestMapping("/bus")
@Scope(value = "prototype")
public class RealTimeQueryHandler {

    @Resource
    private SbBusRouteService sbBusRouteService;
    @Resource
    private SbBusPositionService sbBusPositionService;
    @Resource
    private SbRouteService sbRouteService;
    @Resource
    private SbGpsService sbGpsService;
    @Resource
    private SbRouteStationService sbRouteStationService;

    /**
     * 获取每条路线的站点信息
     *
     * @return 路线和站点信息
     */
    @RequestMapping(value = "/route/station", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultWrapper getStationByRoute() {
        ResultWrapper resultWrapper;
        try {
            //获取所有路线站点信息
            resultWrapper = sbRouteStationService.findAllRouteStation();
        } catch (Exception e){
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;

    }

    /**
     * 测试阶段关闭时间段检测
     * 查询所有路线的校车当然定位信息
     *
     * @return resultWrapper
     */
    @RequestMapping(value = "/track", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultWrapper getPositionByRoute() {
        //创建待返回的封装对象
        ResultWrapper resultWrapper;
        try {
            //获取结果
            resultWrapper = sbBusPositionService.findAllEffectiveRoutePosition();
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
        //返回结果对象
        return resultWrapper;
    }

}
