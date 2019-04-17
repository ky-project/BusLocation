package com.ky.gps.service.inter;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 路线对应站点Service接口
 */
public interface SbRouteStationService {
    /**
     * 查找路线id对应的所有有效站点
     * @param routeId 路线id
     * @return 所有有效站点List集合
     */
    List<Map<String, Object>> findStationByRouteId(Integer routeId);
}
