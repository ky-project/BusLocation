package com.ky.gps.service.inter;

import com.ky.gps.entity.SbBusPosition;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据校车Id查询该校车所有位置信息
     * @param busId 校车id
     * @return 返回存放位置基本信息的list
     */
    List<Map<String, Object>> findAllPositionByBusId(String busId);
}
