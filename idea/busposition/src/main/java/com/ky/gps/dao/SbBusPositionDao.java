package com.ky.gps.dao;

import com.ky.gps.entity.SbBusPosition;

/**
 * @author Daye
 * 校车位置表的Dao
 */
public interface SbBusPositionDao {

    /**
     * 插入校车位置记录
     * @param sbBusPosition 校车位置对象
     */
    void savePosition(SbBusPosition sbBusPosition);
}
