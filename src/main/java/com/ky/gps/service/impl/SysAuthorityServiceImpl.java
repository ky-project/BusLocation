package com.ky.gps.service.impl;

import com.ky.gps.dao.SysAuthorityDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysAuthority;
import com.ky.gps.service.SysAuthorityService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限service层-实现类
 * @author Daye
 */
@Service
public class SysAuthorityServiceImpl implements SysAuthorityService {

    private SysAuthorityDao sysAuthorityDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<String> findAllSaName() {
        return sysAuthorityDao.findAllSaName();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper insertSelective(SysAuthority sysAuthority) {
        sysAuthorityDao.insertSelective(sysAuthority);
        return ResultWrapperUtil.setSuccessOf(sysAuthority);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updateByPrimaryKeySelective(SysAuthority sysAuthority) {
        sysAuthorityDao.updateByPrimaryKeySelective(sysAuthority);
        return ResultWrapperUtil.setSuccessOf(sysAuthority);
    }

    @Autowired
    public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
        this.sysAuthorityDao = sysAuthorityDao;
    }
}
