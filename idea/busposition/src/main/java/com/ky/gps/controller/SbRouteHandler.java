package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.inter.SbBusPositionService;
import com.ky.gps.service.inter.SbRouteService;
import com.ky.gps.service.inter.SbRouteStationService;
import com.ky.gps.util.ResultWrapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Daye
 */
@Controller
@RequestMapping(value = "/route")
public class SbRouteHandler {
    /** 日志打印对象 */
    private final static Logger LOGGER = LoggerFactory.getLogger(SbRouteHandler.class);

    @Resource
    private SbRouteService sbRouteService;
    @Resource
    private SbRouteStationService sbRouteStationService;
    @Resource
    private SbBusPositionService sbBusPositionService;

    /**
     * 根据路线id查询该路线的校车所有定位信息
     * 左闭右开
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/one/position", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findRoutePositionByRouteIdAndStartIndex(@RequestParam(value = "routeId") Integer routeId,
                                                    @RequestParam(value = "startIndex")Integer startIndex) {
        ResultWrapper resultWrapper;
        try{
            //检验参数是否合法
            if(null == routeId || 0 >= routeId
                    || null == startIndex || 0 > startIndex){
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "参数不合法,startIndex>0,startIndex>=0");
            }else {
                //接收service层封装好的json对象
                resultWrapper = sbBusPositionService.findAllEffectiveRoutePositionByRouteId(routeId, startIndex);
            }
        }catch (Exception e){
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }


    /**
     * 根据路线id查询该路线的所有站点信息
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/station/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findRouteStationByRouteId(@RequestParam(value = "routeId") Integer routeId) {
        ResultWrapper resultWrapper;
        try {
            //判断routeId是否合法
            if(null == routeId || 0 >= routeId){
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "routeId>0");
            }else {
                //获取站点信息
                resultWrapper = sbRouteStationService.findStationByRouteId(routeId);
            }
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }

    /**
     * 查询路线的id和name
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/base/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper findRouteNameAndId() {
        ResultWrapper resultWrapper;
        try {
            //获得封装对象
            resultWrapper = sbRouteService.findAllIdAndName();
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }
}
