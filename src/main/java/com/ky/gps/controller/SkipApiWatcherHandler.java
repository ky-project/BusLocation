package com.ky.gps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daye
 * 跳转到api文档接口
 */
@RequestMapping("/api")
@Controller
public class SkipApiWatcherHandler {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String goToApiIndex(){
        return "api/index_api";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String goToAdminApi(){
        return "api/admin_api";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String goToUserApi(){
        return "api/user_api";
    }

    @RequestMapping(value = "/realTimeQuery", method = RequestMethod.GET)
    public String goToRealTimeQueryApi(){
        return "api/real_time_query_api";
    }

    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public String goToDeptApi(){
        return "api/dept_api";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String goToVerifyApi(){
        return "api/verify_api";
    }
}
