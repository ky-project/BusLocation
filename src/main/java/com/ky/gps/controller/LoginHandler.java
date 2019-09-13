package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.entity.SysUser;
import com.ky.gps.enums.SysParamEnum;
import com.ky.gps.service.SysLogService;
import com.ky.gps.service.SysUserService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysLogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户登录处理器
 *
 * @author Daye
 */
@RequestMapping("/login")
@Controller
public class LoginHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginHandler.class);

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysLogService sysLogService;

    /**
     * 用户登录接口
     *
     * @param workId     用户编号
     * @param password   用户密码
     * @param verifyCode 验证码
     * @param loginType  登录类型
     * @param request    请求域
     * @return 返回json格式数据
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper login(@RequestParam(value = "workId") String workId,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "verifyCode") String verifyCode,
                               @RequestParam(value = "loginType") String loginType,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        ResultWrapper resultWrapper = null;
        if (request.getSession().getAttribute("verifyCodeValue") == null) {
            //无验证码
            response.setStatus(400);
            return ResultWrapperUtil.setErrorOf(ErrorCode.VERIFY_EMPTY_ERROR);
        } else if (verifyCode.toUpperCase().equals(request.getSession().getAttribute("verifyCodeValue").toString().toUpperCase())) {
            //验证码正确
            request.getSession().removeAttribute("verifyCodeValue");

            //获取用户
            Subject subject = SecurityUtils.getSubject();
            //如果已登录一个账号，则注销
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            //设置登录类型
            subject.getSession().setAttribute(SysParamEnum.SESSION_LOGIN_TYPE.toString(), loginType);
            //设置用户的账号密码
            UsernamePasswordToken token = new UsernamePasswordToken(workId, password);
            try {
                //进行登录验证
                subject.login(token);
            } catch (DisabledAccountException dax) {
                //账号无效
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.LOGIN_FAILURE, "用户名已经被禁用");
            } catch (UnknownAccountException uae) {
                //账号错误
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.LOGIN_FAILURE, "账号不存在");
            } catch (IncorrectCredentialsException ice) {
                //密码错误
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.LOGIN_FAILURE, "密码错误");
            } catch (Exception e) {
                //其他异常
                LOGGER.error(e.getMessage(), e);
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.LOGIN_FAILURE, "未知异常");
            }
            //判断用户是否登录成功
            if (subject.isAuthenticated()) {
                //获取Session中的值
                @SuppressWarnings("unchecked")
                Map<String, Object> user = (Map<String, Object> )request.getSession().getAttribute(SysParamEnum.SESSION_USER_NAME.toString());
                //获取封装好的syslog对象
                SysLog sysLog = SysLogUtil.initSysLog(user, request);
                //将基本日志信息存入session中
                request.getSession().setAttribute(SysLogUtil.SESSION_SYSLOG, sysLog);
                //将用户操作记录记入数据库
                sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "登录", "/user/login", "成功登录"));
                //将存放用户的基本信息的map封装进result中
                resultWrapper = ResultWrapperUtil.setSuccessOf(user);
            } else if (!subject.isAuthenticated()){
                response.setStatus(400);
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.Login_ERROR);
            }
            return resultWrapper;
        } else {
            //验证码错误
            response.setStatus(400);
            return ResultWrapperUtil.setErrorOf(ErrorCode.VERIFY_ERROR);
        }
    }

    @RequestMapping(value = "/warning", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper loginWarning(HttpServletResponse response) {
        response.setStatus(400);
        return ResultWrapperUtil.setErrorOf(ErrorCode.LOGIN_WARNING, "请先登录");
    }
}
