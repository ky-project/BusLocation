package com.ky.gps.controller.manage;

import com.ky.gps.service.SbRouteStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路线站点管理
 * @author Darren
 */
@Controller
@RequestMapping("/m/routeStation")
public class SbRouteStationManageHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(SbRouteStationManageHandler.class);

    @Autowired
    private SbRouteStationService sbRouteStationService;
}
