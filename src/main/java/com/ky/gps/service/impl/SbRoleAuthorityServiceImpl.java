package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRoleAuthorityDao;
import com.ky.gps.dao.SysAuthorityDao;
import com.ky.gps.dao.SysRoleDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRoleAuthority;
import com.ky.gps.entity.SysAuthorityExtractAttribute;
import com.ky.gps.service.SbRoleAuthorityService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SysAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色权限service层-实现类
 *
 * @author Daye
 */
@Service
public class SbRoleAuthorityServiceImpl implements SbRoleAuthorityService {

    private SbRoleAuthorityDao sbRoleAuthorityDao;
    private SysRoleDao sysRoleDao;
    private SysAuthorityDao sysAuthorityDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updateByRoleId(Integer roleId, List<Integer> idList, List<Integer> needDeleteIdList) {
        if (idList != null && idList.size() > 0) {
            //批量添加权限
            sbRoleAuthorityDao.batchSaveRoleIdAndAuthorityId(roleId, idList);
        }
        if (needDeleteIdList != null && needDeleteIdList.size() > 0) {
            //批量将权限置为无效
            sbRoleAuthorityDao.batchUpdateValidByRoleId(roleId, needDeleteIdList, 0);
        }
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findAllAuthorityByRoleId(Integer roleId) {
        List<Integer> authorityIdList = sbRoleAuthorityDao.findAuthorityIdByRoleId(roleId);
        List<Map<String, Object>> allAuthority = sysAuthorityDao.findAll();
        Map<String, List<SysAuthorityExtractAttribute>> listMapToJsonObj =
                SysAuthorityUtil.findListMapToJsonObjFilterByAuthorityIdList(allAuthority, authorityIdList);
        return ResultWrapperUtil.setSuccessOf(listMapToJsonObj);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findAuthorityIdByRoleId(Integer roleId) {
        List<Integer> authorityIdList = sbRoleAuthorityDao.findAuthorityIdByRoleId(roleId);
        return ResultWrapperUtil.setSuccessOf(authorityIdList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper batchSaveRoleIdAndAuthorityId(Integer roleId, List<Integer> authorityIdList) {
        if (authorityIdList != null && authorityIdList.size() > 0) {
            sbRoleAuthorityDao.batchSaveRoleIdAndAuthorityId(roleId, authorityIdList);
        }
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<String> findSaNameBySrSource(List<String> roles) {
        if (roles == null || roles.size() <= 0) {
            return null;
        }
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
    public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
        this.sysAuthorityDao = sysAuthorityDao;
    }

    @Autowired
    public void setSbRoleAuthorityDao(SbRoleAuthorityDao sbRoleAuthorityDao) {
        this.sbRoleAuthorityDao = sbRoleAuthorityDao;
    }

    @Autowired
    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }
}
