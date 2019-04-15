package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRouteDao;
import com.ky.gps.entity.SbRoute;
import com.ky.gps.service.inter.SbRouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Daye
 * 路线实体类的Service接口实现类
 */
@Service
public class SbRouteServiceImpl implements SbRouteService {

    @Resource
    private SbRouteDao sbRouteDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public SbRoute findBaseInfoById(Integer id) {
        return sbRouteDao.findBaseInfoById(id);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public String findNameById(Integer id) {
        return sbRouteDao.findNameById(id);
    }
}
