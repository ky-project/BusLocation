package com.ky.gps.service.impl;

import com.ky.gps.dao.SbUserBusDao;
import com.ky.gps.dao.SbUserRoleDao;
import com.ky.gps.dao.SysUserDao;
import com.ky.gps.entity.*;
import com.ky.gps.service.inter.SysUserService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * SysUserService接口的实现类，业务逻辑处理
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SbUserRoleDao sbUserRoleDao;
    @Resource
    private SbUserBusDao sbUserBusDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updatePassword(SysUser sysUser) {
        sysUserDao.updatePassword(sysUser);
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findRealNameByPasswordAndUserId(String password, Integer userId) {
        String realName = sysUserDao.findRealNameByPasswordAndUserId(password, userId);
        return ResultWrapperUtil.setSuccessOf(realName);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findSelfBaseInfoByUserId(Integer userId) {
        List<Map<String, Object>> selfBaseInfo = sysUserDao.findSelfBaseInfoByUserId(userId);
        return ResultWrapperUtil.setSuccessOf(selfBaseInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updateUserBaseInfo(SysUser sysUser) {
        //更新用户信息
        sysUserDao.updateUserBaseInfo(sysUser);
        //返回json对象
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findRealNameById(Integer id) {
        //查询真实姓名，不存在则为null
        String realName = sysUserDao.findRealNameById(id);
        //封装realName返回json对象
        return ResultWrapperUtil.setSuccessOf(realName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper deleteUserByUserId(Integer userId, String updateBy) {
        //删除用户和校车的对应关系
        sbUserBusDao.deleteUserBusByUserId(userId,updateBy);
        //删除用户和角色的对应关系
        sbUserRoleDao.deleteUserRoleByUserId(userId, updateBy);
        //删除用户
        sysUserDao.deleteUserByUserId(userId, updateBy);
        //返回json对象
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper saveUserBaseInfo(SbUserRole sbUserRole) {
        //添加用户信息
        sysUserDao.saveUserBaseInfo(sbUserRole.getSysUser());
        //添加用户对应的角色信息
        sbUserRoleDao.saveUserRole(sbUserRole);
        //将添加完成后该用户的id返回
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findUserByPages(Integer startIndex, Integer pageSize) {
        //获取所有用户的信息集合
        List<Map<String, Object>> userList = sysUserDao.findUserByPages(startIndex, pageSize);
        //返回json对象
        return ResultWrapperUtil.setSuccessOf(userList);
    }

    @Transactional(rollbackFor = Exception.class, readOnly =  true)
    @Override
    public ResultWrapper findUserList() {
        //获取所有用户信息集合
        List<Map<String, Object>> userList = sysUserDao.findAllUser();
        //返回json对象
        return ResultWrapperUtil.setSuccessOf(userList);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Map<String, Object> adminUserLogin(Map<String, Object> map) {
        return sysUserDao.findAdminBaseInfoByWordIdAndPassword(map);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Map<String, Object> simpleUserLogin(Map<String, Object> map) {
        return sysUserDao.findBaseInfoByWorkIdAndPassword(map);
    }
}
