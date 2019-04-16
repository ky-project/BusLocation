package com.ky.gps.dao;

/**
 * @author Daye
 * GPS设备的Dao
 */
public interface SbGpsDao {

    /**
     * 根据校车id来查询GPS的id
     * @param busId 校车id
     * @return GPS的id
     */
    String findIdByBusId(Integer busId);
}
