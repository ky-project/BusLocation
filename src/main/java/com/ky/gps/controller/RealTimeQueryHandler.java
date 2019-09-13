package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbBusPositionService;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.util.JudgeTimeUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @author Daye
 * 根据路线查询校车实时位置
 */
@Controller
@RequestMapping("/bus")
public class RealTimeQueryHandler {
    /** 日志打印对象 */
    private final static Logger LOGGER = LoggerFactory.getLogger(RealTimeQueryHandler.class);

    @Resource
    private SbBusPositionService sbBusPositionService;
    @Resource
    private SbRouteStationService sbRouteStationService;

    /**
     * 根据路线id查询该路线对应的bus的最新的定位
     *
     * @param routeId 路线id
     * @return Json数据
     */
    @RequestMapping(value = "/route/track", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findRealTimeNewPositionByRouteId(Integer routeId){
        ResultWrapper resultWrapper;
        try{
            //空值判断
            if(null == routeId || routeId <= 0){
                //如果存在空值，返回错误信息
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.EMPTY_ERROR, "routeId <= 0");
            } else{
                //反之查询结果并返回
                resultWrapper = sbBusPositionService.findNewPositionByRouteId(routeId);
            }
        }catch (Exception e){
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }

    /**
     * 获取每条路线的站点信息
     *
     * @return 路线和站点信息
     */
    @RequestMapping(value = "/route/station", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultWrapper getRealTimeStationByRoute() {
        ResultWrapper resultWrapper;
        try {
            //获取所有路线站点信息 原方法findAllRouteStation(未过滤)
            resultWrapper = sbRouteStationService.findRealTimeAllRouteStation(JudgeTimeUtil.getWeek(), String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
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
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        //返回结果对象
        return resultWrapper;
    }

}
