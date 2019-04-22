package com.ky.gps.dao;

import com.ky.gps.entity.SbRoute;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 校车路线Dao
 */
@Repository
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

    /**
     * 查询所有路线的基本信息
     * @return 所有路线基本信息的list
     */
    List<Map<String, Object>> findAllBaseInfo();

}
