package com.ky.gps.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author Daye
 *
 * 角色表Dao数据访问层
 */
public interface SysRoleDao {

    /**
     * 根据id查询角色名
     * @param roleId 待查询的角色id
     * @return name值
     */
    String findNameById(@Param("roleId") Integer roleId);
}
