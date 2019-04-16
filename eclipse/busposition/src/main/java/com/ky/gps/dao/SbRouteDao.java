package com.ky.gps.dao;

import com.ky.gps.entity.SbRoute;

/**
 * @author Daye
 * 校车路线Dao
 */
public interface SbRouteDao {

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
