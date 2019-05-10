package com.ky.gps.service.inter;

import com.ky.gps.entity.ResultWrapper;

/**
 * @author Daye
 * 路线对应站点Service接口
 */
public interface SbRouteStationService {
    /**
     * 查找路线id对应的所有有效站点
     *
     * @param routeId 路线id
     * @return 所有有效站点List集合
     */
    ResultWrapper findStationByRouteId(Integer routeId);

    /**
     * 查询所有路线和其对应的站点信息
     *
     * @return 返回根据站点发车时间排序好并封装完成的Result
     */
    ResultWrapper findAllRouteStation();

}
