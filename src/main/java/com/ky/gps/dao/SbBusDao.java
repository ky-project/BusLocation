package com.ky.gps.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 校车类的Dao
 */
public interface SbBusDao {

    /**
     * 查询所有校车记录
     * @return 返回校车信息集合
     */
    List<Map<String, Object>> find();

    /**
     * 根据id查询校车的车牌号
     * @param id 待查询的校车id
     * @return 车牌号
     */
    String findPlateNumberById(@Param("id") Integer id);
}
