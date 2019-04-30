package com.ky.gps.dao;

import com.ky.gps.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 系统用户Dao类
 */
public interface SysUserDao {
    /**
     * 根据用户教工号、密码来查询普通用户信息
     *
     * @param map workId:教工号;password:密码
     * @return 返回查询结果并封装进map中
     */
    Map<String, Object> findBaseInfoByWorkIdAndPassword(Map<String, Object> map);

    /**
     * 根据用户教工号、密码来查询管理员信息
     *
     * @param map workId:教工号;password:密码
     * @return 返回查询结果封装进map中
     */
    Map<String, Object> findAdminBaseInfoByWordIdAndPassword(Map<String, Object> map);

    /**
     * 查询所有用户信息
     *
     * @return keys={id, departmentName, workId, realName, idCode, phone, email}
     */
    List<Map<String, Object>> findAllUser();

    /**
     * 分页查询用户信息
     *
     * @param startIndex 起始查询位置
     * @param pageSize   查询条数
     * @return keys={id, departmentName, workId, realName, idCode, phone, email}
     */
    List<Map<String, Object>> findUserByPages(Integer startIndex, Integer pageSize);

    /**
     * 添加用户的基本信息
     * department_id, workid, real_name, password,
     * salt, id_code, phone, email, account_date, created_by, updated_by, valid
     *
     * @param sysUser 待添加的用户信息
     * @return 返回刚添加的用户id
     */
    Integer saveUserBaseInfo(SysUser sysUser);
}
