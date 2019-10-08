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
     * 更新绑定信息
     *
     * @param sbBusRoute 待更新的对象
     */
    void update(SbBusRoute sbBusRoute);

    /**
     * 根据id查询绑定信息
     *
     * @param id 查询id
     * @return 返回绑定信息
     */
    Map<String, Object> findById(@Param("id") Integer id);

    /**
     * 插入记录
     *
     * @param sbBusRoute 带插入的对象
     */
    void save(SbBusRoute sbBusRoute);

    /**
     * 根据id更新有效位
     *
     * @param id    id
     * @param valid 有效位
     */
    void updateValidById(@Param("id") Integer id, @Param("valid") Integer valid);

    /**
     * 根据路线id和星期查询路线的校车绑定信息
     *
     * @param routeId 路线id
     * @param week    星期
     * @return 返回信息集合
     */
    List<Map<String, Object>> findByRouteId(@Param("routeId") Integer routeId, @Param("week") String week);

    /**
     * 查询所有的联系
     * 不包含备注
     *
     * @return 对象list
     */
    List<SbBusRoute> findAllRelation();

    /**
     * 根据routeId查询该路线的运营时间，即startTime和endTime
     *
     * @param routeId 路线id
     * @return keys={startTime, endTime}
     */
    Map<String, Object> findStartAndEndTimeByRouteId(@Param("routeId") Integer routeId);
}
