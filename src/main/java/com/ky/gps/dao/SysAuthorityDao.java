package com.ky.gps.dao;

import com.ky.gps.entity.SysAuthority;

import java.util.List;

/**
 * 权限dao层
 * @author Daye
 */
public interface SysAuthorityDao {

    /**
     * 查询所有权限码
     * @return 返回字符list
     */
    List<String> findAllSaName();

    /**
     * 添加权限 - 检验空值
     *
     * @param sysAuthority 待添加的权限对象
     */
    void insertSelective(SysAuthority sysAuthority);

    /**
     * 更新权限 - 检验空值
     * @param sysAuthority 待修改的权限对象
     */
    void updateByPrimaryKeySelective(SysAuthority sysAuthority);

}