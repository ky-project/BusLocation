package com.ky.gps.service.inter;

import java.util.Map;

/**
 * @author Daye
 * 系统用户Service接口
 */
public interface SysUserService {

    /**
     * 根据用户账号/教工号、密码来查询用户信息
     * 因为登录时需要做一些特殊处理，因此这里只返回map，由controller来进行处理
     * @param map workId:教工号;password:密码
     * @return 返回查询结果并封装进map中
     */
    Map<String, Object> simpleUserLogin(Map<String, Object> map);

    /**
     * 根据用户教工号、密码来查询管理员信息
     * @param map workId:教工号;password:密码
     * @return 返回查询结果封装进map中
     */
    Map<String, Object> adminUserLogin(Map<String, Object> map);
}
