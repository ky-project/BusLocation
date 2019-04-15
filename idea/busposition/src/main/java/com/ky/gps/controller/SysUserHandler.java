package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.service.inter.SysUserService;
import com.ky.gps.util.IpUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daye
 * 普通用户登录操作控制器
 */
@Controller
@RequestMapping(value = "/user")
@Scope(value = "prototype")
public class SysUserHandler {

    @Resource
    private SysUserService sysUserService;

    /**
     * 普通用户登录接口
     * @param userName 登录账号:用户账号/职工编号
     * @param password 登录密码
     * @param request request请求
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper loginCheck(@RequestParam(value = "userName") String userName,
                                    @RequestParam(value = "password") String password,
                                    HttpServletRequest request) {
        //存放登录账号/密码的键值对
        Map<String, Object> map = new HashMap<>(16);
        //待返回的结果对象
        ResultWrapper resultWrapper;
        //对参数进行判断
        if ("".equals(userName) || null == userName || "".equals(password) || null == password) {
            return ResultWrapperUtil.setErrorOf(ErrorCode.EMPTY_ERROR);
        }
        //将账号密码存入map中
        map.put("userName", userName);
        map.put("password", password);
        try {
            //获取用户验证后的信息，如果通过，则获取基本信息，反之为null
            Map<String, Object> baseInfo = sysUserService.simpleUserLogin(map);

            //对返回结果进行判断
            if (null == baseInfo) {
                //如果为null，则说明验证失败
                return ResultWrapperUtil.setErrorOf(ErrorCode.Login_ERROR);
            }
            //备份一份基本信息，封装到SysLog对象中，用于存入session，方便之后操作的查询和日志打印
            SysLog sysLog = new SysLog();
            //获取用户id
            sysLog.setSysUserId((Integer) baseInfo.get("sysUserId"));
            //获取用户登录账号/教工号
            sysLog.setUserName(userName);
            //获取真实姓名
            sysLog.setRealName(baseInfo.get("realName").toString());
            //获取部门名
            sysLog.setDepartmentName(baseInfo.get("departmentName").toString());
            //TODO 获取ip和mac地址
            sysLog.setIpAddress(IpUtil.getIpAddress(request));

            //将基本日志信息存入session中
            request.getSession().setAttribute("sysLog", sysLog);
            //将存放用户的基本信息的map封装进result中
            resultWrapper = ResultWrapperUtil.setSuccessOf(baseInfo);
        }catch (Exception e){
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            e.printStackTrace();
        }

        return resultWrapper;
    }
}
