package com.ky.gps.service.inter;

import com.ky.gps.entity.SbBusRoute;

import java.util.List;

/**
 * @author Daye
 */
public interface SbBusRouteService {

    /**
     * 查询所有的联系
     * 不包含备注
     * @return 对象list
     */
    List<SbBusRoute> findAllRelation();
}
