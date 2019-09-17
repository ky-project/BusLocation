package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRoleAuthority;

import java.util.List;

/**
 * 角色权限service层-接口类
 *
 * @author Daye
 */
public interface SbRoleAuthorityService {

    /**
     * 根据角色代码名查询角色所拥有的所有权限
     * @param roles 角色代码名list
     * @return 返回权限代码list
     */
    List<String> findSaNameBySrSource(List<String> roles);

    /**
     * 插入角色权限记录
     * @param sbRoleAuthority 待插入的角色权限对象
     * @return
     */
    ResultWrapper insertSelective(SbRoleAuthority sbRoleAuthority);

    /**
     * 更新角色权限记录
     * @param sbRoleAuthority 待更新的权限对象
     */
    ResultWrapper updateByPrimaryKeySelective(SbRoleAuthority sbRoleAuthority);
}
