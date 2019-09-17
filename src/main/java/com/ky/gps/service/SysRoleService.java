package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;

/**
 * @author Daye
 * 角色表Service层接口类
 */
public interface SysRoleService {

    /**
     * 查询所有角色信息
     * @return 返回json格式数据
     */
    ResultWrapper findAllRole();

    /**
     * 根据id查询角色名
     *
     * @param roleId 待查询的角色id
     * @return 封装name值的json对象
     */
    ResultWrapper findNameById(Integer roleId);
}
