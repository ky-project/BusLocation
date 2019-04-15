package com.ky.gps.service.inter;

import com.ky.gps.entity.SbRoute;

/**
 * @author Daye
 * 路线实体类的Service接口
 */
public interface SbRouteService {

    /**
     * 根据id查询路线的基本信息
     * @param id 查询id
     * @return 返回对象
     */
    SbRoute findBaseInfoById(Integer id);

    /**
     * 根据id查询路线名
     * @param id 查询id
     * @return 路线名
     */
    String findNameById(Integer id);
}
