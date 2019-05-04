package com.ky.gps.service.impl;

import com.ky.gps.dao.SysUserDao;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysUser;
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

    @Override
    public ResultWrapper saveUserBaseInfo(SysUser sysUser) {
        Integer userId = sysUserDao.saveUserBaseInfo(sysUser);
        return ResultWrapperUtil.setSuccessOf(userId);
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
