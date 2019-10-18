package com.ky.gps.controller;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbRouteService;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.service.SbStationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 站点处理器
 * @author Darren
 */
@RequestMapping("/routeStation")
@Controller
public class SbRouteStationHandler {

    @Autowired
    private SbStationService sbStationService;
    @Autowired
    private SbRouteService sbRouteService;
    @Autowired
    private SbRouteStationService sbRouteStationService;

    /**
     * 根据路线id查询路线的所有站点信息
     */
    @PermissionName(displayName = "站点查询", group = "站点管理")
    @RequiresPermissions("station:query")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultWrapper listByRouteId(@RequestBody Map<String, Object> params){
        Integer routeId = (Integer) params.get("routeId");
        return sbRouteStationService.findStationByRouteId(routeId);
    }
}
