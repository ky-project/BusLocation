package com.ky.gps.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户角色工具类
 * @author Daye
 */
public class SbUserRoleUtil {

    /**
     * 判断用户是否拥有角色，有-checked=true，无-checked=false
     * @param userRoleIdList 用户拥有角色id集合
     * @param roleList 角色集合
     * @return 返回处理好后的roleList
     */
    public static List<Map<String, Object>> checkUserHasRole(List<Integer> userRoleIdList, List<Map<String, Object>> roleList){
        //处理用户没有任何角色的情况
        if(userRoleIdList == null){
            userRoleIdList = new ArrayList<>();
        }
        //遍历角色集合
        for (Map<String, Object> role : roleList) {
            //获取角色id
            Integer id = (Integer) role.get("id");
            //判断用户是否拥有该角色
            if(userRoleIdList.contains(id)){
                role.put("checked", true);
            } else{
                role.put("checked", false);
            }
        }

        return roleList;
    }
}
