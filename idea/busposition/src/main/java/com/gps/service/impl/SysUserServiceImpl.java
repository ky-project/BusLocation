package com.gps.service.impl;

import com.gps.dao.SysUserDao;
import com.gps.entity.SysUser;
import com.gps.service.inter.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Daye
 * SysUserService接口的实现类，事务处理
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public SysUser findByJobNumberAndPwd(Map<String, Object> map) {
        return sysUserDao.findByJobNumberAndPwd(map);
    }
}
