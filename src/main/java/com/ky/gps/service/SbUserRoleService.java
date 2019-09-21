package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbUserRole;

import java.util.List;

/**
 * @author Daye
 *
 * 用户和角色表多对多关系的Service接口
 */
public interface SbUserRoleService {


    /**
     * 根据用户id批量更新用户角色记录
     * @param id 用户id
     * @param needDeleteIdList 待删除的角色id
     * @param needAddIdList 待添加的角色id
     * @return 返回json对象
     */
    ResultWrapper updateUserRoleByUserId(Integer id, List<Integer> needDeleteIdList, List<Integer> needAddIdList);

    /**
     * 根据用户id查询其拥有的所有角色id
     * @param userId 用户id
     * @return 角色id集合
     */
    ResultWrapper findRoleIdByUserId(Integer userId);

    /**
     * 根据用户id查询该用户的所有角色拥有情况
     * @param userId 用户id
     * @return 返回json对象
     */
    ResultWrapper findUserRolesStatusByUserId(Integer userId);

    /**
     * 查询所有用户和其角色信息
     * @return 返回json对象
     */
    ResultWrapper findAllUserAndRole();

    /**
     * 根据用户id查询该用户的所有角色
     * @param id 用户id
     * @return 返回角色名list
     */
    List<String> findSysRoleSrSourceBySysUserId(Integer id);

    /**
     * 根据userId和roleId将用户和角色关联起来
     *
     * @param sbUserRole 待保存的对象
     */
    ResultWrapper saveUserRole(SbUserRole sbUserRole);
}
