package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbUserRole;

import java.util.List;

/**
 * @author Daye
 *
 * 用户和角色表多对多关系的Service接口
 */
public interface SbUserRoleService {

    /**
     * 根据用户id查询该用户的所有角色
     * @param id 用户id
     * @return 返回角色名list
     */
    List<String> findSysRoleSrSourceBySysUserId(Integer id);

    /**
     * 根据userId和roleId将用户和角色关联起来
     *
     * @param sbUserRole 待保存的对象
     */
    ResultWrapper saveUserRole(SbUserRole sbUserRole);
}
