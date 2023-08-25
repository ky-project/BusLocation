package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBusPosition;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 校车位置类的Dao
 */
public interface SbBusPositionService {

    /**
     * 根据路线id查询路线最新定位
     *
     * @param routeId 路线id
     * @return 返回最新定位信息
     */
    Map<String, Object> findByRouteId(Integer routeId);

    /**
     * 插入校车位置记录
     *
     * @param sbBusPosition 校车位置对象
     */
    void savePosition(SbBusPosition sbBusPosition);

    /**
     * 根据校车Id查询该校车所有位置信息
     *
     * @param busId 校车id
     * @return 返回存放位置基本信息的list
     */
    List<Map<String, Object>> findAllPositionByBusId(String busId);

    /**
     * 查找本星期,本时间段的所有路线的校车定位信息
     *
     * @return 返回过滤好并封装进结果封装对象中
     */
    ResultWrapper findAllEffectiveRoutePosition();

    /**
     * 查询本星期，本时间段，该路线id的路线的校车定位信息
     *
     * @param routeId    待查询的路线id
     * @param startIndex 开始查询的索引位置(不包括索引位置)
     * @return 返回处理完成的结果封装对象中
     */
    ResultWrapper findAllEffectiveRoutePositionByRouteId(Integer routeId, Integer startIndex);

    /**
     * 将当天位置表中的内容迁移到历史表，并清空当天位置表
     */
    void deletePositionAndMoveToHis();

    /**
     * 根据路线id查询该路线的bus最新的定位数据
     *
     * @param routeId 路线id
     * @return Json对象
     */
    ResultWrapper findNewPositionByRouteId(Integer routeId);
}
