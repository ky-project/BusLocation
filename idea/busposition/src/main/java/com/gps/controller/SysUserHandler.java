package com.gps.controller;

import com.gps.entity.SysUser;
import com.gps.entity.Teacher;
import com.gps.service.inter.SysUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daye
 */
@Controller
@RequestMapping(value = "/user")
@Scope(value = "prototype")
public class SysUserHandler {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public SysUser loginCheck(SysUser sysUser){
        Map<String, Object> map = new HashMap<>(16);
        //TODO map need put
        SysUser loginCheckUser = sysUserService.findByJobNumberAndPwd(map);
        return loginCheckUser;
    }
}
