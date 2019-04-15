package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBusRoute;
import com.ky.gps.service.inter.SbBusPositionService;
import com.ky.gps.service.inter.SbBusRouteService;
import com.ky.gps.service.inter.SbRouteService;
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

    /**
     * 根据路线查询所有对应校车的位置信息
     * @return 返回所有路线的车辆位置信息
     */
    @RequestMapping(value = "/track", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultWrapper getPositionByRoute(){
        ResultWrapper resultWrapper;
        //存放路线和路线对应的校车位置list，实际存放的是路线名和位置的mapList
        List<Map<String, Object>> routePositionList = new ArrayList<>();
        try {
            //得到所有路线与校车关联信息
            List<SbBusRoute> allRelation = sbBusRouteService.findAllRelation();
            //遍历该list
            for (SbBusRoute sbBusRoute : allRelation) {
                //根据每辆车的id获取位置list
                List<Map<String, Object>> positionList = sbBusPositionService.findAllPositionByBusId(sbBusRoute.getSbBus().getId());
                //获取路线名
                String routeName = sbRouteService.findNameById(sbBusRoute.getSbRoute().getId());
                //存放该路线和对应位置的信息
                Map<String, Object> routePositionMap = new HashMap<>(16);
                //路线实时位置
                routePositionMap.put("trackRoute", positionList);
                //放入路线名
                routePositionMap.put("routeName", routeName);
                //放入List中
                routePositionList.add(routePositionMap);
            }
            //将最终的list存入对象中等待返回
            resultWrapper = ResultWrapperUtil.setSuccessOf(routePositionList);
        }catch (Exception e){
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR, e.getMessage());
            e.printStackTrace();
        }
        return resultWrapper;
    }
}
