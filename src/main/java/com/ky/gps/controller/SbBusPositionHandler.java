package com.ky.gps.controller;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbBusPositionService;
import com.ky.gps.util.ResultWrapperUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 校车定位处理器
 * @author Darren
 */
@RequestMapping("/busPosition")
@Controller
public class SbBusPositionHandler {

    @Autowired
    private SbBusPositionService sbBusPositionService;

    /**
     * 根据路线id查询最新的定位信息
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findNewPositionByRouteId(@RequestBody Map<String, Object> params){
        Integer routeId = (Integer) params.get("routeId");
        Map<String, Object> newPosition = sbBusPositionService.findByRouteId(routeId);
        return ResultWrapperUtil.setSuccessOf(newPosition);
    }

}
