package com.ky.gps.service.inter;

/**
 * @author Daye
 * GPS的业务处理Service接口
 */
public interface SbGpsService {
    /**
     * 根据校车id来查询GPS的id
     * @param busId 校车id
     * @return GPS的id
     */
    String findIdByBusId(Integer busId);
}
