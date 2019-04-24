package com.ky.gps.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 路线站点对应Dao
 */
@Repository
public interface SbRouteStationDao {

    /**
     * 查找路线id对应的所有有效站点
     * @param routeId 路线id
     * @return 所有有效站点List集合
     */
    List<Map<String, Object>> findStationByRouteId(Integer routeId);

    /**
     * 查询所有路线对应的站点信息
     * 但未根据发车时间排序，因此程序需要对发车时间进行排序
     *
     * @return 所有路线对应站点的信息，keys={routeName, stationName, longitude, latitude, departTime}
     */
    List<Map<String, Object>> findAllRouteStation();
}
