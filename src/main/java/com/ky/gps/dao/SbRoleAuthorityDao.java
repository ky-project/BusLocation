package com.ky.gps.dao;

import com.ky.gps.entity.SbRoleAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限中间表dao层
 * @author Daye
 */
public interface SbRoleAuthorityDao {

    /**
     * 根据角色id将记录置为无效
     * @param roleId 角色id
     * @param value 待更新的值
     */
    void updateValidByRoleId(@Param("roleId") Integer roleId, @Param("value") Integer value);

    /**
     * 根据角色代码名查询角色所拥有的所有权限
     * @param roles 角色代码名list
     * @return 返回权限代码list
     */
    List<String> findSaNameBySrSource(@Param("roles") List<String> roles);

    /**
     * 插入角色权限记录
     * @param sbRoleAuthority 待插入的角色权限对象
     */
    void insertSelective(SbRoleAuthority sbRoleAuthority);

    /**
     * 更新角色权限记录
     * @param sbRoleAuthority 待更新的权限对象
     */
    void updateByPrimaryKeySelective(SbRoleAuthority sbRoleAuthority);

}