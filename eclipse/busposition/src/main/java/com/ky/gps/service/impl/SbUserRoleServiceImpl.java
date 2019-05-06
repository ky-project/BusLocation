package com.ky.gps.service.impl;

import com.ky.gps.dao.SbUserRoleDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbUserRole;
import com.ky.gps.service.inter.SbUserRoleService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Daye
 *
 * 用户和角色表多对多关系的Service接口的实现类
 */
@Service
public class SbUserRoleServiceImpl implements SbUserRoleService {

    @Resource
    private SbUserRoleDao sbUserRoleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper saveUserRole(SbUserRole sbUserRole) {
        sbUserRoleDao.saveUserRole(sbUserRole);
        return ResultWrapperUtil.setSuccessOf(null);
    }
}
