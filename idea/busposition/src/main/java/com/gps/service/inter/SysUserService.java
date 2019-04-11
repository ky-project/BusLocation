package com.gps.service.inter;

import com.gps.entity.SysUser;

import java.util.Map;

/**
 * @author Daye
 * 系统用户Service接口
 */
public interface SysUserService {

    /**
     * 根据教工号、密码来查询教师信息
     * @param map
     * @return 返回查询结果并封装进对象中
     */
    SysUser findByJobNumberAndPwd(Map<String, Object> map);
}
