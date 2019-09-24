package com.ky.gps.service.impl;

import com.ky.gps.dao.SbStationDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbStationService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 站点表service层-实现类
 * @author Daye
 */
@Service
public class SbStationServiceImpl implements SbStationService {

    private SbStationDao sbStationDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findFuzzyQueryBySbsStation(String sbsStation) {
        List<Map<String, Object>> stationList = sbStationDao.findFuzzyBySvsStation(sbsStation);
        return ResultWrapperUtil.setSuccessOf(stationList);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findAll() {
        List<Map<String, Object>> stationList = sbStationDao.findAll();
        return ResultWrapperUtil.setSuccessOf(stationList);
    }

    @Autowired
    public void setSbStationDao(SbStationDao sbStationDao) {
        this.sbStationDao = sbStationDao;
    }
}
