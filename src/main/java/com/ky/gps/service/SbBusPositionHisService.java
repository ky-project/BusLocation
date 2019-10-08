package com.ky.gps.service;

import java.util.List;
import java.util.Map;

/**
 * @author Darren
 */
public interface SbBusPositionHisService {
    /**
     * 根据日期和路线id查询定位
     *
     * @param routeId    路线id
     * @param recodeTime 创建日期
     * @return 返回定位信息集合
     */
    List<Map<String, Object>> findByRouteIdAndCreatedDate(Integer routeId, String recodeTime);
}
