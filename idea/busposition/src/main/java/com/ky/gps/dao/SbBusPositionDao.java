package com.ky.gps.dao;

import com.ky.gps.entity.SbBusPosition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 校车位置表的Dao
 */
@Repository
public interface SbBusPositionDao {

    /**
     * 插入校车位置记录
     * @param sbBusPosition 校车位置对象
     */
    void savePosition(SbBusPosition sbBusPosition);

    /**
     * 根据GPSId查询该校车所有位置信息
     * @param gpsId GPSid
     * @return 返回存放位置基本信息的list
     */
    List<Map<String, Object>> findAllPositionByBusId(String gpsId);

    /**
     * 查找本星期的所有路线的校车定位信息
     * @param week 本星期的星期几，星期一、星期二...
     * @return 返回所有路线的校车定位信息,Keys={routeName, startTime, endTime, recodeTime, longitude, latitude, velocity, direction}
     */
    List<Map<String, Object>> findAllRoutePosition(String week);
}
