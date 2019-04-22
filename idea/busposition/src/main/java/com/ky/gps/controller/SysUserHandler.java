package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.service.inter.SysLogService;
import com.ky.gps.service.inter.SysUserService;
import com.ky.gps.util.IpUtil;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysLogUtil;
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

    private final static String[] LOGIN_TYPE = {"admin", "simple"};

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysLogService sysLogService;

    /**
     * 普通用户登录接口
     *
     * @param workId   登录账号:职工编号
     * @param password 登录密码
     * @param request  request请求W
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper adminLoginCheck(@RequestParam(value = "workId") String workId,
                                         @RequestParam(value = "password") String password,
                                         HttpServletRequest request) {
        return loginWrapper(workId, password, request, LOGIN_TYPE[0]);
    }

    private ResultWrapper loginWrapper(@RequestParam("workId") String workId,
                                       @RequestParam("password") String password,
                                       HttpServletRequest request,
                                       String loginType) {
        //对参数进行判断
        if ("".equals(workId) || null == workId || "".equals(password) || null == password) {
            return ResultWrapperUtil.setErrorOf(ErrorCode.EMPTY_ERROR);
        }
        //待返回的结果对象
        ResultWrapper resultWrapper;
        //存放登录账号/密码的键值对
        Map<String, Object> map = new HashMap<>(16);
        //将账号密码存入map中
        map.put("workId", workId);
        map.put("password", password);
        try {
            //获取用户验证后的信息，如果通过，则获取基本信息，反之为null
            Map<String, Object> baseInfo = null;
            //管理员/普通用户登录
            if(LOGIN_TYPE[0].equals(loginType)){
                //管理员登录
                baseInfo = sysUserService.adminUserLogin(map);
            } else if(LOGIN_TYPE[1].equals(loginType)){
                //普通用户登录
                baseInfo = sysUserService.simpleUserLogin(map);
            }
            //对返回结果进行判断
            if (null == baseInfo) {
                //如果为null，则说明验证失败
                return ResultWrapperUtil.setErrorOf(ErrorCode.Login_ERROR);
            }
            //获取封装好的syslog对象
            SysLog sysLog = SysLogUtil.initSysLog(baseInfo, request);
            //将基本日志信息存入session中
            request.getSession().setAttribute("sysLog", sysLog);
            //将用户操作记录记入数据库
            sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "登录", "登录模块", "成功登录"));
            //将存放用户的基本信息的map封装进result中
            resultWrapper = ResultWrapperUtil.setSuccessOf(baseInfo);
        } catch (Exception e) {
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            e.printStackTrace();
        }
        return resultWrapper;
    }

    /**
     * 普通用户登录接口
     *
     * @param workId   登录账号:职工编号
     * @param password 登录密码
     * @param request  request请求
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper loginCheck(@RequestParam(value = "workId") String workId,
                                    @RequestParam(value = "password") String password,
                                    HttpServletRequest request) {
        return loginWrapper(workId, password, request, LOGIN_TYPE[1]);
    }


}
