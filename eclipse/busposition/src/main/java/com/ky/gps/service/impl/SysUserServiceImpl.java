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
import java.util.Map;

/**
 * @author Daye
 * SysUserService接口的实现类，业务逻辑处理
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Map<String, Object> simpleUserLogin(Map<String, Object> map) {
        sysUserDao.findBaseInfoByWorkIdAndPassword(map);
        return sysUserDao.findBaseInfoByWorkIdAndPassword(map);
    }
}
