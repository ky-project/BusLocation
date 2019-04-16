package com.ky.gps.service.impl;

import com.ky.gps.dao.SbBusPositionDao;
import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.service.inter.SbBusPositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * SbBusPositionService接口的实现类
 * 对校车位置的业务处理
 */
@Service
public class SbBusPositionServiceImpl implements SbBusPositionService {

    @Resource
    private SbBusPositionDao sbBusPositionDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void savePosition(SbBusPosition sbBusPosition) {
        sbBusPositionDao.savePosition(sbBusPosition);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<Map<String, Object>> findAllPositionByBusId(String busId) {
        return sbBusPositionDao.findAllPositionByBusId(busId);
    }
}
