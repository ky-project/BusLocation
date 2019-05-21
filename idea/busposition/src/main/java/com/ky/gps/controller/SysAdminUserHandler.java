package com.ky.gps.controller;

import com.ky.gps.entity.*;
import com.ky.gps.service.DepartmentService;
import com.ky.gps.service.SysLogService;
import com.ky.gps.service.SysRoleService;
import com.ky.gps.service.SysUserService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Daye
 * 管理员操作控制层
 */
@Controller
@RequestMapping(value = "/admin")
public class SysAdminUserHandler {
    /**
     * 日志打印对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SysAdminUserHandler.class);
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private SysLogService sysLogService;

    /**
     * 更新用户的基本信息
     *
     * @param sysUser 待更新的用户对象
     * @return json格式数据
     */
    @RequestMapping(value = "/update/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper updateUserBaseInfo(SysUser sysUser, HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //判断id是否为空，且id是否存在
            if (null == sysUser.getId() || 0 >= sysUser.getId()) {
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.UPDATE_ERROR, "id为空或不合法");
            } else {
                if (null == sysUserService.findRealNameById(sysUser.getId()).getData()) {
                    resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.UPDATE_ERROR, "用户不存在");
                } else {
                    //设置更新者workId
                    sysUser.setUpdatedBy(((SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG)).getWorkId());
                    //更新对象
                    resultWrapper = sysUserService.updateUserBaseInfo(sysUser);
                    //日志记录
                    sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "更新用户基本信息", "/admin/update/info", "更新用户(id):" + sysUser.getId()));
                }
            }
        } catch (Exception e) {
            //异常处理
            LOGGER.error("", e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 根据用户id删除用户
     * 不是真删除，而是将Valid设为0，即无效
     *
     * @param userId 用户id
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/del/id", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper deleteUserByUserId(Integer userId, HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //TODO 判断是否有删除权限
            //判断是否为空值
            if (null == userId || 0 >= userId) {
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "存在空值");
            } else {
                //查询id是否存在
                if (null == sysUserService.findRealNameById(userId).getData()) {
                    resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "id不存在！");
                } else {
                    //调用删除方法，更新者workId从session中获取
                    resultWrapper = sysUserService.deleteUserByUserId(userId, ((SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG)).getWorkId());
                    //日志记录
                    sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "删除用户", "/admin/del/id", "删除用户(id):" + userId));
                }
            }
        } catch (Exception e) {
            //异常处理
            LOGGER.error("", e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);

        }
        return resultWrapper;
    }

    /**
     * 添加用户基本信息
     *
     * @param sysUser 用户信息
     * @return 返回刚添加的用户id
     */
    @RequestMapping(value = "/info/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper saveUserBaseInfo(SysUser sysUser, Integer roleId, HttpServletRequest request) {
        ResultWrapper resultWrapper;
        try {
            //判断roleId和sysUser.getDepartment().getId()是否为空，且是否存在
            if (!isEffectiveDepartmentAndRoleId(sysUser, roleId)) {
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "部门id或角色id不存在");
            } else {
                //TODO 判断用户是否有权限
                //获取session中的用户信息
                SysLog sysLog = (SysLog) request.getSession().getAttribute(SysLogUtil.SESSION_SYSLOG);
                //将修改者workId记录到sysUser中
                sysUser.setCreatedBy(sysLog.getWorkId());
                sysUser.setUpdatedBy(sysLog.getWorkId());
                //设置账户创建日期
                sysUser.setAccountDate(new Date());
                //TODO 密码加密
                sysUser.setSalt(sysUser.getPassword());
                //判断是否存在空值
                if (isSavedAllInfoNull(sysUser, roleId)) {
                    resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "用户信息存在空值");
                } else {
                    //创建用户和角色对应的实体类
                    SbUserRole sbUserRole = new SbUserRole();
                    //set用户对象
                    sbUserRole.setSysUser(sysUser);
                    //set角色对象，将roleId存入new的SysRole对象中
                    sbUserRole.setSysRole(new SysRole(roleId));
                    //set创建者，从用户对象中获取
                    sbUserRole.setCreatedBy(sysUser.getCreatedBy());
                    //set更新者，从用户对象中获取
                    sbUserRole.setUpdatedBy(sysUser.getUpdatedBy());
                    //添加用户信息，并将返回的json对象赋值给resultWrapper
                    resultWrapper = sysUserService.saveUserBaseInfo(sbUserRole);
                    //记录到用户操作记录
                    sysLogService.saveSysLog(SysLogUtil.setOperateInfo(request, "添加用户", "/admin/info/add", "添加用户:" + sysUser.getWorkId()));
                }
            }
        } catch (Exception e) {
            //异常日志
            LOGGER.error("", e);
            //异常提示
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 判断部门和角色id是否存在
     *
     * @param sysUser 传入的用户信息
     * @param roleId  角色id
     * @return 有效:true; 无效:false
     */
    private Boolean isEffectiveDepartmentAndRoleId(SysUser sysUser, Integer roleId) {
        if (null == sysUser.getDepartment()
                || 0 >= sysUser.getDepartment().getId() || null == sysUser.getDepartment().getId()
                || null == roleId || 0 >= roleId) {
            return false;
        }
        //判断部门和角色是否存在
        return (null != departmentService.findNameById(sysUser.getDepartment().getId()).getData()
                && null != sysRoleService.findNameById(roleId).getData());
    }

    /**
     * 判断待save的数据是否存在空值
     *
     * @param sysUser 待保存的用户信息
     * @param roleId  用户对应的角色id
     * @return 空:true;不为空:false
     */
    private Boolean isSavedAllInfoNull(SysUser sysUser, Integer roleId) {
        return (null == roleId || 0 >= roleId
                || null == sysUser.getDepartment().getId() || 0 >= sysUser.getDepartment().getId()
                || "".equals(sysUser.getWorkId()) || null == sysUser.getWorkId()
                || "".equals(sysUser.getRealName()) || null == sysUser.getRealName()
                || "".equals(sysUser.getPassword()) || null == sysUser.getPassword()
                || "".equals(sysUser.getSalt()) || null == sysUser.getSalt()
                || "".equals(sysUser.getIdCode()) || null == sysUser.getIdCode()
                || "".equals(sysUser.getPhone()) || null == sysUser.getPhone()
                || "".equals(sysUser.getEmail()) || null == sysUser.getEmail()
                || null == sysUser.getAccountDate()
                || "".equals(sysUser.getCreatedBy()) || null == sysUser.getCreatedBy()
                || "".equals(sysUser.getUpdatedBy()) || null == sysUser.getUpdatedBy());
    }

    /**
     * 分页查询用户信息
     *
     * @param pageNow  当前页数
     * @param pageSize 一页查询的数量
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/info/list/pages", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper userListPages(Integer pageNow, Integer pageSize) {
        ResultWrapper resultWrapper;
        try {
            //判断参数是否合法
            if (null == pageNow || null == pageSize
                    || 0 >= pageNow || 0 >= pageSize) {
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR, "参数不合法！");
            } else {
                //算出需要查询位置索引
                Integer startIndex = (pageNow - 1) * pageSize;
                //获取查询结果
                resultWrapper = sysUserService.findUserByPages(startIndex, pageSize);
            }
        } catch (Exception e) {
            //异常日志记录
            LOGGER.error("", e);
            //异常信息返回
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }

    /**
     * 查询所有用户的基本信息
     *
     * @return 返回json格式
     */
    @RequestMapping(value = "/info/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper userList() {
        //创建待返回的json对象
        ResultWrapper resultWrapper;
        try {
            //获取存放所有用户的json封装类
            ResultWrapper userList = sysUserService.findUserList();
            //判断data是否为空
            if (userList.getData() != null) {
                //不为空，则将返回的userList赋值给resultWrapper
                resultWrapper = userList;
            } else {
                //为空则提示异常
                resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SELECT_ERROR);
            }
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
            LOGGER.error("", e);
        }
        return resultWrapper;
    }
}
