package com.ky.gps.service.impl;

import com.ky.gps.dao.SbBusDao;
import com.ky.gps.entity.SbBus;
import com.ky.gps.service.SbBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 校车service-实体类
 * @author Darren
 */
@Service
public class SbBusServiceImpl implements SbBusService {

    @Autowired
    private SbBusDao sbBusDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> insert(SbBus sbBus) {
        sbBusDao.insert(sbBus);
        return sbBusDao.findById(sbBus.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        sbBusDao.updateValidById(id, 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> update(SbBus sbBus) {
        sbBusDao.update(sbBus);
        return sbBusDao.findById(sbBus.getId());
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<Map<String, Object>> find() {
        return sbBusDao.find();
    }
}
