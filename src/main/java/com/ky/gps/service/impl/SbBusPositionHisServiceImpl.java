package com.ky.gps.service.impl;

import com.ky.gps.dao.SbBusPositionHisDao;
import com.ky.gps.service.SbBusPositionHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Darren
 */
@Service
public class SbBusPositionHisServiceImpl implements SbBusPositionHisService {

    @Autowired
    private SbBusPositionHisDao sbBusPositionHisDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<Map<String, Object>> findByRouteIdAndCreatedDate(Integer routeId, String recodeTime) {
        return sbBusPositionHisDao.findByRouteIdAndCreatedDate(routeId, recodeTime);
    }
}
