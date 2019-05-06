package com.ky.gps.service.impl;

import com.ky.gps.dao.SysRoleDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.inter.SysRoleService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Daye
 *
 * 角色表Service层接口的实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public ResultWrapper findNameById(Integer roleId) {
        //获取该id的角色name
        String roleName = sysRoleDao.findNameById(roleId);
        //将name封装进json对象中返回
        return ResultWrapperUtil.setSuccessOf(roleName);
    }
}
