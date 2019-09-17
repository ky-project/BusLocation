package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;

/**
 * @author Daye
 * 角色表Service层接口类
 */
public interface SysRoleService {

    /**
     * 根据角色id回复角色及用户角色中间表和角色权限中间表的记录
     * @param id 角色id
     * @return json对象
     */
    ResultWrapper rollbackById(Integer id);

    /**
     * 根据角色id删除角色及用户角色中间表和角色权限中间表的记录
     *
     * @param id 角色id
     * @return Json对象
     */
    ResultWrapper deleteById(Integer id);

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
