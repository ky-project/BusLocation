package com.ky.gps.service.impl;

import com.ky.gps.dao.SbBusRouteDao;
import com.ky.gps.entity.SbBusRoute;
import com.ky.gps.service.inter.SbBusRouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Daye
 * 校车与路线对应联系的Service
 */
@Service
public class SbBusRouteServiceImpl implements SbBusRouteService {

    @Resource
    private SbBusRouteDao sbBusRouteDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<SbBusRoute> findAllRelation() {
        List<SbBusRoute> allRelation = sbBusRouteDao.findAllRelation();
        //TODO 对记录进行筛选，判断是否在可查询时间段
        return allRelation;
    }
}
