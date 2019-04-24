package com.ky.gps.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Daye
 * 系统用户Dao类
 */
@Repository
public interface SysUserDao {
    /**
     * 根据用户教工号、密码来查询普通用户信息
     * @param map workId:教工号;password:密码
     * @return 返回查询结果并封装进map中
     */
    Map<String, Object> findBaseInfoByWorkIdAndPassword(Map<String, Object> map);

    /**
     * 根据用户教工号、密码来查询管理员信息
     * @param map workId:教工号;password:密码
     * @return 返回查询结果封装进map中
     */
    Map<String, Object> findAdminBaseInfoByWordIdAndPassword(Map<String, Object> map);

}
