package com.ky.gps.dao;

import com.ky.gps.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Daye
 * 系统用户Dao类
 */
@Repository
public interface SysUserDao {
    /**
     * 根据用户账号/教工号、密码来查询教师信息
     * @param map userName:用户账号/教工号 and password:密码
     * @return 返回查询结果并封装进对象中
     */
    Map<String, Object> findBaseInfoByLoginNameOrJobNumberAndPwd(Map<String, Object> map);
}
