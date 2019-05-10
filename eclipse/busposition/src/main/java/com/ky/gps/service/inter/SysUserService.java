package com.ky.gps.service.inter;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysUser;

import java.util.List;
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

    /**
     * 获取所有用户的信息集合
     * @return 将集合封装到json对象中返回
     */
    ResultWrapper findUserList();

    /**
     * 分页查询用户信息
     *
     * @param startIndex 起始查询位置
     * @param pageSize       查询条数
     * @return keys={id, departmentName, workId, realName, idCode, phone, email}
     */
    ResultWrapper findUserByPages(Integer startIndex, Integer pageSize);

    /**
     * 添加用户的基本信息
     *
     * @param sysUser 待添加的用户信息
     * @return 返回刚添加的用户id
     */
    ResultWrapper saveUserBaseInfo(SysUser sysUser);
}
