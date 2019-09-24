package com.ky.gps.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 站点信息Dao
 */
public interface SbStationDao {

    /**
     * 查询所有站点信息
     * @return 返回站点信息list
     */
    List<Map<String, Object>> findAll();

    /**
     * 根据站点名模糊查询所有站点list
     * @param sbsStation 站点名
     * @return 返回站点集合
     */
    List<Map<String, Object>> findFuzzyBySvsStation(@Param("sbsStation") String sbsStation);
}
