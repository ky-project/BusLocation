package com.ky.gps.service.inter;

import com.ky.gps.entity.SbBusPosition;

/**
 * @author Daye
 * 校车位置类的Dao
 */
public interface SbBusPositionService {

    /**
     * 插入校车位置记录
     *
     * @param sbBusPosition 校车位置对象
     */
     void savePosition(SbBusPosition sbBusPosition);
}
