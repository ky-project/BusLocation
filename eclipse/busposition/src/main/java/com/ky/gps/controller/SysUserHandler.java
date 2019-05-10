package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.entity.SysUser;
import com.ky.gps.service.inter.SysLogService;
import com.ky.gps.service.inter.SysUserService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class SysUserHandler {

    /** 日志打印对象 */
    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserHandler.class);
    /** 用户身份数组 */
    private final static String[] LOGIN_TYPE = {"admin", "simple"};

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysLogService sysLogService;

    /**
     * 添加用户基本信息
     *
     * @param sysUser 用户信息
     * @return 返回刚添加的用户id
     */
    @RequestMapping(value = "/info/add")
    @ResponseBody
    public ResultWrapper saveUserBaseInfo(SysUser sysUser){
        ResultWrapper resultWrapper;
        try {
            System.out.println(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询用户信息
     * @param pageNow 当前页数
     * @param pageSize 一页查询的数量
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/info/list/pages", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper userListPages(Integer pageNow, Integer pageSize){
        ResultWrapper resultWrapper;
        try{
            //算出需要查询位置索引
            Integer startIndex = (pageNow - 1) * pageSize;
            //获取查询结果
            resultWrapper = sysUserService.findUserByPages(startIndex, pageSize);
        }catch (Exception e){
            //异常日志记录
            LOGGER.error(e.getMessage());
            //异常信息返回
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 查询所有用户的基本信息
     * @return 返回json格式
     */
    @RequestMapping(value = "/info/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper userList(){
        //创建待返回的json对象
        ResultWrapper resultWrapper;
        try{
            //获取存放所有用户的json封装类
            ResultWrapper userList = sysUserService.findUserList();
            //判断data是否为空
            if(userList.getData() != null){
                //不为空，则将返回的userList赋值给resultWrapper
                resultWrapper = userList;
            }else{
                //为空则提示异常
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR);
            }
        }catch (Exception e){
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error(e.getMessage());
        }
        return resultWrapper;
    }

    /**
     * 管理员用户登录接口
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
            LOGGER.error(e.getMessage());
        }
        return resultWrapper;
    }

}
