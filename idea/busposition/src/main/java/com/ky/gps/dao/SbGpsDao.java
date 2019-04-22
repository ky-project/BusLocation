package com.ky.gps.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Daye
 * GPS设备的Dao
 */
@Repository
public interface SbGpsDao {

    /**
     * 根据校车id来查询GPS的id
     * @param busId 校车id
     * @return GPS的id
     */
    String findIdByBusId(Integer busId);
}
