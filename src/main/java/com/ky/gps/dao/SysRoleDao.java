package com.ky.gps.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 *
 * 角色表Dao数据访问层
 */
public interface SysRoleDao {

    /**
     * 查询所有角色信息
     * @return 返回角色信息list
     */
    List<Map<String, Object>> findAllRole();

    /**
     * 根据id查询角色名
     * @param roleId 待查询的角色id
     * @return name值
     */
    String findNameById(@Param("roleId") Integer roleId);
}
