package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbRoutePositionService;
import com.ky.gps.util.ResultWrapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Daye
 * 路线行驶轨迹Controller层
 */

@Controller
@RequestMapping(value = "/routeposition")
public class SbRoutePositionHandler {
    /** 日志打印对象 */
    private final static Logger LOGGER = LoggerFactory.getLogger(SbRoutePositionHandler.class);

    @Resource
    private SbRoutePositionService sbRoutePositionService;

    /**
     * 根据id查询该路线的行驶轨迹
     *
     * @param routeId 路线id
     * @return 返回Json数据
     */
    @RequestMapping(value = "/find/one", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findLonAndLatByRouteId(Integer routeId){
        ResultWrapper resultWrapper;
        try{
            //空值判断
            if(null == routeId || routeId <= 0){
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR,"routeId > 0");
            } else{
                resultWrapper = sbRoutePositionService.findLonAndLatByRouteId(routeId);
            }//空值判断end
        }catch (Exception e){
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }
}
