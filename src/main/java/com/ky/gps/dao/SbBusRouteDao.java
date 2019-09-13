package com.ky.gps.dao;

import com.ky.gps.entity.SbBusRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * SbBusRoute实体(校车与路线对应关系)的Dao
 */
public interface SbBusRouteDao {

    /**
     * 查询所有的联系
     * 不包含备注
     * @return 对象list
     */
    List<SbBusRoute> findAllRelation();

    /**
     * 根据routeId查询该路线的运营时间，即startTime和endTime
     * @param routeId 路线id
     * @return keys={startTime, endTime}
     */
    Map<String, Object> findStartAndEndTimeByRouteId(@Param("routeId") Integer routeId);
}
