package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRoleAuthorityDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRoleAuthority;
import com.ky.gps.service.SbRoleAuthorityService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色权限service层-实现类
 * @author Daye
 */
@Service
public class SbRoleAuthorityServiceImpl implements SbRoleAuthorityService {

    private SbRoleAuthorityDao sbRoleAuthorityDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<String> findSaNameBySrSource(List<String> roles) {
        return sbRoleAuthorityDao.findSaNameBySrSource(roles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper insertSelective(SbRoleAuthority sbRoleAuthority) {
        sbRoleAuthorityDao.insertSelective(sbRoleAuthority);
        return ResultWrapperUtil.setSuccessOf(sbRoleAuthority);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updateByPrimaryKeySelective(SbRoleAuthority sbRoleAuthority) {
        sbRoleAuthorityDao.updateByPrimaryKeySelective(sbRoleAuthority);
        return ResultWrapperUtil.setSuccessOf(sbRoleAuthority);
    }

    @Autowired
    public void setSbRoleAuthorityDao(SbRoleAuthorityDao sbRoleAuthorityDao) {
        this.sbRoleAuthorityDao = sbRoleAuthorityDao;
    }
}
