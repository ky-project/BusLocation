package com.ky.gps.util;

import com.ky.gps.entity.SysAuthorityExtractAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限对象工具类
 *
 * @author Daye
 */
public class SysAuthorityUtil {

    /**
     * 将查询出来的mapList中的saGroup抽取出来存入提取属性的权限对象中
     * 同时根据权限id集合对抽取对象进行过滤，将checked设为true
     * 并放入map中返回
     * 其中，map的key={id, saGroup, saDisplayName}
     *
     * @param allAuthority 所有权限list
     * @return 返回抽取完成的map
     */
    public static Map<String, List<SysAuthorityExtractAttribute>> findListMapToJsonObjFilterByAuthorityIdList(List<Map<String, Object>> allAuthority,
                                                                                                              List<Integer> authorityIdList) {
        //判断map是否为空
        if (allAuthority == null || allAuthority.size() == 0) {
            return null;
        }
        //定义待返回的map
        Map<String, List<SysAuthorityExtractAttribute>> listMap = new HashMap<>();
        //获取第0索引位的权限组
        String saGroup = allAuthority.get(0).get("saGroup").toString();
        //定义权限属性抽取对象list
        List<SysAuthorityExtractAttribute> attributeList = new ArrayList<>();
        //遍历map
        for (Map<String, Object> map : allAuthority) {
            //如果操作组不相同，说明一组抽取完毕
            if (!map.get("saGroup").toString().equals(saGroup)) {
                //将上一组的记录存入listMap
                listMap.put(saGroup, attributeList);
                //重置组名
                saGroup = map.get("saGroup").toString();
                //重置attributeList
                attributeList = new ArrayList<>();
            }
            //将值提取出来存入对象
            Integer id = (Integer) map.get("id");
            SysAuthorityExtractAttribute sysAuthorityExtractAttribute =
                    new SysAuthorityExtractAttribute();
            sysAuthorityExtractAttribute.setId(id);
            sysAuthorityExtractAttribute.setSaDisplayName(map.get("saDisplayName").toString());
            sysAuthorityExtractAttribute.setChecked(authorityIdList.contains(id));
            //提取完成后加入list中
            attributeList.add(sysAuthorityExtractAttribute);
        }
        //将最后一组的记录存入listMap
        listMap.put(saGroup, attributeList);
        return listMap;
    }

    /**
     * 将查询出来的mapList中的saGroup抽取出来存入提取属性的权限对象中，并放入map中返回
     * 其中，map的key={id, saGroup, saDisplayName}
     *
     * @param allAuthority 所有权限list
     * @return 返回抽取完成的map
     */
    public static Map<String, List<SysAuthorityExtractAttribute>> findListMapToJsonObj(List<Map<String, Object>> allAuthority) {
        //判断map是否为空
        if (allAuthority == null || allAuthority.size() == 0) {
            return null;
        }
        //定义待返回的map
        Map<String, List<SysAuthorityExtractAttribute>> listMap = new HashMap<>();
        //获取第0索引位的权限组
        String saGroup = allAuthority.get(0).get("saGroup").toString();
        //定义权限属性抽取对象list
        List<SysAuthorityExtractAttribute> attributeList = new ArrayList<>();
        //遍历map
        for (Map<String, Object> map : allAuthority) {
            //如果操作组不相同，说明一组抽取完毕
            if (!map.get("saGroup").toString().equals(saGroup)) {
                //将上一组的记录存入listMap
                listMap.put(saGroup, attributeList);
                //重置组名
                saGroup = map.get("saGroup").toString();
                //重置attributeList
                attributeList = new ArrayList<>();
            }
            //将值提取出来存入对象
            SysAuthorityExtractAttribute sysAuthorityExtractAttribute =
                    new SysAuthorityExtractAttribute();
            sysAuthorityExtractAttribute.setId((Integer) map.get("id"));
            sysAuthorityExtractAttribute.setSaDisplayName(map.get("saDisplayName").toString());
            sysAuthorityExtractAttribute.setChecked(false);
            //提取完成后加入list中
            attributeList.add(sysAuthorityExtractAttribute);
        }
        //将最后一组的记录存入listMap
        listMap.put(saGroup, attributeList);
        return listMap;
    }
}
