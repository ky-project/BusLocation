package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysLog;
import com.ky.gps.entity.SysUser;
import com.ky.gps.service.EmailService;
import com.ky.gps.service.SysLogService;
import com.ky.gps.service.SysUserService;
import com.ky.gps.util.RandomUtil;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SendMail;
import com.ky.gps.util.SysLogUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysLogService sysLogService;
    @Autowired
    private EmailService emailService;

    /**
     * 用户忘记密码，通过邮箱来更改密码
     *
     * @param params 参数map
     *               password  待修改的密码
     *               checkCode 验证码
     * @return json格式数据
     */
    @RequestMapping(value = "/modify/pwd/email", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper modifyPasswordByEmail(@RequestBody Map<String, Object> params,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        ResultWrapper resultWrapper;
        String password = params.get("password").toString();
        String checkCode = params.get("checkCode").toString();
        //判断是否存在空值
        if (null == checkCode || "".equals(checkCode)
                || null == password || "".equals(password)) {
            //空值警告
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        } else {
            //获取ssession中的验证码和邮箱
            String[] emailModifyPwd = (String[]) request.getSession().getAttribute("emailModifyPwd");
            //session空值判断
            if (null == emailModifyPwd) {
                resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SYSTEM_ERROR, "无权限修改！", response);
            } else {
                SysUser sysUser = new SysUser();
                sysUser.setPassword(password);
                sysUser.setEmail(emailModifyPwd[0]);
                //邮箱验证，验证码比较
                if (sysUserService.isEffectiveEmail(sysUser.getEmail()) && checkCode.equals(emailModifyPwd[1])) {
                    //根据email查询用户基本信息
                    Map<String, Object> baseInfo = sysUserService.findBaseInfoByEmail(sysUser.getEmail());
                    //TODO 设置加密密码
                    sysUser.setSalt(sysUser.getPassword());
                    //设置最后一次密码修改日期
                    sysUser.setLastPsdDate(new Date());
                    //设置更新者职工编号
                    sysUser.setUpdatedBy(baseInfo.get("workId").toString());
                    //修改密码
                    resultWrapper = sysUserService.modifyPasswordByEmail(sysUser);
                    //将其存入SysLog对象中
                    SysLog sysLog = SysLogUtil.initSysLog(baseInfo, request);
                    //设置用户操作内容
                    SysLogUtil.setOperateInfoByObject(sysLog, "用户通过忘记密码修改密码", "/user/modify/pwd/email", "用户:" + baseInfo.get("workId") + "修改密码成功");
                    //记录日志
                    sysLogService.saveSysLog(sysLog);
                    //清除该session，防止二次修改
                    request.getSession().removeAttribute("emailModifyPwd");
                } else {
                    //返回邮箱不存在提示
                    resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SELECT_ERROR, "验证码错误！", response);
                }
            }
        }
        return resultWrapper;
    }

    /**
     * 发送email给用户邮箱
     * 忘记密码使用
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/send/email", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper sendEmail(@RequestBody Map<String, Object> params,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        ResultWrapper resultWrapper;
        String email = params.get("email").toString();
        //空值判断
        if (null == email || "".equals(email)) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        } else {
            //邮箱验证
            if (sysUserService.isEffectiveEmail(email)) {
                //随机产生验证码
                String checkCode = RandomUtil.randomNumberChar(5);
                //邮箱发送
                emailService.sendEmail(email, checkCode);
                //将邮箱存入session中
                String[] emailModifyPwd = {email, checkCode};
                request.getSession().setAttribute("emailModifyPwd", emailModifyPwd);
                //设置成功信息
                resultWrapper = ResultWrapperUtil.setSuccessOf(null);
                //根据email查询用户基本信息
                Map<String, Object> baseInfo = sysUserService.findBaseInfoByEmail(email);
                //将其存入SysLog对象中
                SysLog sysLog = SysLogUtil.initSysLog(baseInfo, request);
                //设置用户操作内容
                SysLogUtil.setOperateInfoByObject(sysLog, "用户忘记密码请求", "/user/send/email", "发送更改密码url给" + email);
                //记录日志
                sysLogService.saveSysLog(sysLog);
            } else {
                //返回邮箱不存在提示
                resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SELECT_ERROR, "邮箱未绑定账号", response);
            }
        }
        return resultWrapper;
    }

    /**
     * 修改用户自身密码
     *
     * @param params  参数map
     * @param request request域
     * @return json格式数据，data为null
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modify/pwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper modifyPassword(@RequestBody Map<String, Object> params,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        ResultWrapper resultWrapper;
        String oldPassword = params.get("oldPassword").toString();
        String newPassword = params.get("newPassword").toString();
        //判断是否含有空值
        if (null == oldPassword || "".equals(oldPassword)
                || null == newPassword || "".equals(newPassword)) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SELECT_ERROR, "存在空值", response);
        } else {
            //获取Session中的log对象
            SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
            //判断原密码是否正确
            if (null == sysUserService.findRealNameByPasswordAndUserId(oldPassword, sysLog.getUserId()).getData()) {
                resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.UPDATE_ERROR, "原密码错误", response);
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

                //更新session中的信息
                Subject subject = SecurityUtils.getSubject();
                Map<String, Object> user = (Map<String, Object>) subject.getPrincipal();
                user.put("password", sysUser.getPassword());
                //创建一个PrincipalCollection对象
                PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, user.get("password").toString());
                //调用subject的runAs方法，把新的PrincipalCollection放到session里面
                subject.runAs(newPrincipalCollection);

                //设置密码修改日期
                sysUser.setLastPsdDate(new Date());
                //设置更新者workId
                sysUser.setUpdatedBy(sysLog.getWorkId());
                //记录日志
                sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "更改用户密码", "/user/modify/pwd", "修改用户密码(id):" + sysLog.getUserId()));
            }
        }
        return resultWrapper;
    }

    /**
     * 获取自己的个人信息
     *
     * @param request request域
     * @return json格式对象
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/self/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper getUserSelfInfo(HttpServletRequest request) {
        ResultWrapper resultWrapper;
        Map<String, Object> user = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        //获取封装好id对应基本信息的json对象
        resultWrapper = ResultWrapperUtil.setSuccessOf(user);
        return resultWrapper;
    }

    /**
     * 用户更新基本信息
     * realName、idCode、phone、email
     *
     * @param sysUser 更新后的用户对象
     * @param request request域
     * @return json个数数据，data为null
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/update/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper updateSelfInfo(@RequestBody SysUser sysUser, HttpServletRequest request) {
        ResultWrapper resultWrapper;

        //获取session中的log对象
        SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
        //设置待更新的用户id
        sysUser.setId(sysLog.getUserId());
        //设置更新者workId
        sysUser.setUpdatedBy(sysLog.getWorkId());

        //更新用户信息
        Map<String, Object> user = sysUserService.updateUserBaseInfo(sysUser);

        //更新session中的信息
        Subject subject = SecurityUtils.getSubject();
        //创建一个PrincipalCollection对象
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, user.get("password").toString());
        //调用subject的runAs方法，把新的PrincipalCollection放到session里面
        subject.runAs(newPrincipalCollection);

        user.remove("password");
        //封装
        resultWrapper = ResultWrapperUtil.setSuccessOf(user);
        //日志记录
        sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "用户更新自身基本信息", "/user/update/info", "更新用户(id):" + sysUser.getId()));
        return resultWrapper;
    }

}
