package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.entity.SysUser;
import com.ky.gps.service.SysLogService;
import com.ky.gps.service.SysUserService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daye
 * 普通用户操作控制层
 */
@Controller
@RequestMapping(value = "/user")
public class SysUserHandler {

    /**
     * 日志打印对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserHandler.class);
    /**
     * 用户身份数组
     */
    private final static String[] LOGIN_TYPE = {"admin", "simple"};

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysLogService sysLogService;

    /**
     * 修改用户自身密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     request域
     * @return json格式数据，data为null
     */
    @RequestMapping(value = "/modify/pwd")
    @ResponseBody
    public ResultWrapper modifyPassword(String oldPassword, String newPassword, HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //判断是否含有空值
            if (null == oldPassword || "".equals(oldPassword)
                    || null == newPassword || "".equals(newPassword)) {
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "存在空值");
            } else {
                //获取Session中的log对象
                SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
                //判断原密码是否正确
                if (null == sysUserService.findRealNameByPasswordAndUserId(oldPassword, sysLog.getUserId()).getData()) {
                    resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.UPDATE_ERROR, "原密码错误");
                } else {
                    //创建用户对象，将修改后的用户信息存入
                    SysUser sysUser = new SysUser();
                    //设置id
                    sysUser.setId(sysLog.getUserId());
                    //设置新密码
                    sysUser.setPassword(newPassword);
                    //TODO 密码加密
                    sysUser.setSalt(newPassword);
                    //修改密码
                    resultWrapper = sysUserService.updatePassword(sysUser);
                    //设置密码修改日期
                    sysUser.setLastPsdDate(new Date());
                    //设置更新者workId
                    sysUser.setUpdatedBy(sysLog.getWorkId());
                    //记录日志
                    sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "更改用户密码", "/user/modify/pwd", "修改用户密码(id):" + sysLog.getUserId()));
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 获取自己的个人信息
     * id, departmentName, workId, realName, idCode, phone, email
     *
     * @param request request域
     * @return json格式对象
     */
    @RequestMapping(value = "/self/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper getUserSelfInfo(HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //获取session中的log对象
            SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
            resultWrapper = sysUserService.findSelfBaseInfoByUserId(sysLog.getUserId());
            //TODO 获取封装好id对应基本信息的json对象
        } catch (Exception e) {
            //异常处理
            LOGGER.error("", e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 用户更新基本信息
     * realName、idCard、phone、email
     *
     * @param sysUser 更新后的用户对象
     * @param request request域
     * @return json个数数据，data为null
     */
    @RequestMapping(value = "/update/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper updateSelfInfo(SysUser sysUser, HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //获取session中的log对象
            SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
            //设置待更新的用户id
            sysUser.setId(sysLog.getUserId());
            //设置更新者workId
            sysUser.setUpdatedBy(sysLog.getWorkId());
            //更新对象
            resultWrapper = sysUserService.updateUserBaseInfo(sysUser);
            //日志记录
            sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "用户更新自身基本信息", "/user/update/info", "更新用户(id):" + sysUser.getId()));
        } catch (Exception e) {
            //异常处理
            LOGGER.error("", e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 管理员用户登录接口
     *
     * @param workId   登录账号:职工编号
     * @param password 登录密码
     * @param request  request请求
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper adminLoginCheck(@RequestParam(value = "workId") String workId,
                                         @RequestParam(value = "password") String password,
                                         HttpServletRequest request) {
        return loginWrapper(workId, password, request, LOGIN_TYPE[0]);
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

    /**
     * 根据workId和password来进行用户登录验证
     *
     * @param workId    教工号
     * @param password  密码
     * @param request   request域
     * @param loginType 用户类型，admin or simple
     * @return 返回json对象
     */
    private ResultWrapper loginWrapper(String workId,
                                       String password,
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
        String module = "";
        try {
            //获取用户验证后的信息，如果通过，则获取基本信息，反之为null
            Map<String, Object> baseInfo = null;
            //管理员/普通用户登录
            if (LOGIN_TYPE[0].equals(loginType)) {
                //管理员登录
                baseInfo = sysUserService.adminUserLogin(map);
                module = "/user/admin/login";
            } else if (LOGIN_TYPE[1].equals(loginType)) {
                //普通用户登录
                baseInfo = sysUserService.simpleUserLogin(map);
                module = "/user/login";
            }
            //对返回结果进行判断
            if (null == baseInfo) {
                //如果为null，则说明验证失败
                return ResultWrapperUtil.setErrorOf(ErrorCode.Login_ERROR);
            }
            //获取封装好的syslog对象
            SysLog sysLog = SysLogUtil.initSysLog(baseInfo, request);
            //将基本日志信息存入session中
            request.getSession().setAttribute(SysLogUtil.SESSION_SYSLOG, sysLog);
            //将用户操作记录记入数据库
            sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "登录", module, "成功登录"));
            //将存放用户的基本信息的map封装进result中
            resultWrapper = ResultWrapperUtil.setSuccessOf(baseInfo);
        } catch (Exception e) {
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }

}
