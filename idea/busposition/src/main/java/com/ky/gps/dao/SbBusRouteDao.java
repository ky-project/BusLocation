package com.ky.gps.dao;

import com.ky.gps.entity.SbBusRoute;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daye
 * SbBusRoute实体(校车与路线对应关系)的Dao
 */
@Repository
public interface SbBusRouteDao {

    /**
     * 查询所有的联系
     * 不包含备注
     * @return 对象list
     */
    List<SbBusRoute> findAllRelation();
}
