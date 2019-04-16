package com.ky.gps.service.impl;

import com.ky.gps.dao.SbGpsDao;
import com.ky.gps.service.inter.SbGpsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Daye
 * GPS的业务处理Service接口的实现类
 */
@Service
public class SbGpsServiceImpl implements SbGpsService {

    @Resource
    private SbGpsDao sbGpsDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public String findIdByBusId(Integer busId) {
        return sbGpsDao.findIdByBusId(busId);
    }
}
