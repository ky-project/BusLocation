package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbUserRole;
import com.ky.gps.entity.SysUser;

import java.util.Map;

/**
 * @author Daye
 * 系统用户Service接口
 */
public interface SysUserService {

    /**
     * 根据用户账号/教工号、密码来查询用户信息
     * 因为登录时需要做一些特殊处理，因此这里只返回map，由controller来进行处理
     *
     * @param map workId:教工号;password:密码
     * @return 返回查询结果并封装进map中
     */
    Map<String, Object> simpleUserLogin(Map<String, Object> map);

    /**
     * 根据用户教工号、密码来查询管理员信息
     *
     * @param map workId:教工号;password:密码
     * @return 返回查询结果封装进map中
     */
    Map<String, Object> adminUserLogin(Map<String, Object> map);

    /**
     * 获取所有用户的信息集合
     *
     * @return 将集合封装到json对象中返回
     */
    ResultWrapper findUserList();

    /**
     * 分页查询用户信息
     *
     * @param startIndex 起始查询位置
     * @param pageSize   查询条数
     * @return keys={id, departmentName, workId, realName, idCode, phone, email}
     */
    ResultWrapper findUserByPages(Integer startIndex, Integer pageSize);

    /**
     * 添加用户的基本信息
     *
     * @param sbUserRole 存放SysUser和SysRole对象，包含待添加的用户基本信息和用户所对应的部门id
     * @return 返回刚添加的用户id
     */
    ResultWrapper saveUserBaseInfo(SbUserRole sbUserRole);

    /**
     * 根据用户id删除用户
     * 不是真删除，而是将Valid设为0，即无效
     *
     * @param userId   用户id
     * @param updateBy 更新者wordId
     *
     * @return json对象
     */
    ResultWrapper deleteUserByUserId(Integer userId, String updateBy);

    /**
     * 根据用户id查询用户的真实姓名
     *
     * @param id 用户id
     * @return json对象
     */
    ResultWrapper findRealNameById(Integer id);

    /**
     * 更新用户基本信息
     *
     * @param sysUser 待更新的用户对象
     * @return json对象
     */
    ResultWrapper updateUserBaseInfo(SysUser sysUser);

    /**
     * 根据用户id查询该id的所有基本信息
     * keys={id, departmentName, workId, realName, idCode, phone, email}
     *
     * @param userId 用户id
     * @return json对象
     */
    ResultWrapper findSelfBaseInfoByUserId(Integer userId);

    /**
     * 根据用户id和密码查询用户的真实姓名
     *
     * @param password 密码
     * @param userId   用户id
     * @return 返回realName
     */
    ResultWrapper findRealNameByPasswordAndUserId(String password, Integer userId);

    /**
     * 根据id更新用户的密码和加密的密码
     * password和salt
     *
     * @param sysUser 待更新用户对象
     * @return json对象，data为null
     */
    ResultWrapper updatePassword(SysUser sysUser);
}
