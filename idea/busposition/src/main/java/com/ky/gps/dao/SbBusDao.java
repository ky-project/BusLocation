package com.ky.gps.dao;

/**
 * @author Daye
 * 校车类的Dao
 */
public interface SbBusDao {

    /**
     * 根据id查询校车的车牌号
     * @param id 待查询的校车id
     * @return 车牌号
     */
    String findPlateNumberById(Integer id);
}
