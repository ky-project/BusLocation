package com.ky.gps.service;

import java.util.List;
import java.util.Map;

/**
 * 用户路线中间表service-接口类
 * @author Darren
 */
public interface SbUserBusService {
    /**
     * 根据用户id查询该用户能查看的所有路线id和name
     * @param userId 用户id
     * @return 返回map
     */
    List<Map<String, Object>> findRouteIdAndRouteNameByUserId(Integer userId);
}
