package com.ky.gps.dao;

import com.ky.gps.entity.SysUser;
import org.apache.ibatis.annotations.Param;

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
    List<Map<String, Object>> findUserByPages(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 添加用户的基本信息
     * department_id, workid, real_name, password,
     * salt, id_code, phone, email, account_date, created_by, updated_by, valid
     *
     * @param sysUser 待添加的用户信息
     * @return 返回刚添加的用户id
     */
    Integer saveUserBaseInfo(SysUser sysUser);

    /**
     * 根据用户id删除用户
     * 不是真删除，而是将Valid设为0，即无效
     *
     * @param userId   用户id
     * @param updateBy 更新者workId
     */
    void deleteUserByUserId(@Param("userId") Integer userId, @Param("updateBy") String updateBy);

    /**
     * 根据用户id查询用户的真实姓名
     *
     * @param id 用户id
     * @return 用户的真实姓名
     */
    String findRealNameById(@Param("id") Integer id);

    /**
     * 更新用户基本信息
     *
     * @param sysUser 待更新的用户对象
     */
    void updateUserBaseInfo(SysUser sysUser);

    /**
     * 根据用户id查询该id的所有基本信息
     *
     * @param userId 用户id
     * @return 用户基本信息list-keys={id, departmentName, workId, realName, idCode, phone, email}
     */
    List<Map<String, Object>> findSelfBaseInfoByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id和密码查询用户的真实姓名
     *
     * @param password 密码
     * @param userId   用户id
     * @return 返回realName
     */
    String findRealNameByPasswordAndUserId(@Param("password") String password, @Param("userId") Integer userId);

    /**
     * 根据id更新用户的密码和加密的密码
     * password、salt、lastPsdDate和updateBy
     *
     * @param sysUser 待更新用户对象
     */
    void updatePassword(SysUser sysUser);

    /**
     * 根据email查询用户id
     * @param email 邮箱
     * @return 返回id
     */
    Integer findIdByEmail(String email);

    /**
     * 根据email查询用户基本信息
     * @param email 邮箱
     * @return 用户信息map,keys={sysUserId, realName, workId, departmentName}
     */
    Map<String, Object> findBaseInfoByEmail(String email);

    /**
     * 根据email来修改密码
     * @param sysUser 存放email、password、salt、lastPsdDate和updatedBy数据的对象
     */
    void updatePasswordByEmail(SysUser sysUser);
}
