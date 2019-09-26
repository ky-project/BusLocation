package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRouteStationDao;
import com.ky.gps.dao.SbStationDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbStation;
import com.ky.gps.service.SbStationService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 站点表service层-实现类
 *
 * @author Daye
 */
@Service
public class SbStationServiceImpl implements SbStationService {

    private SbStationDao sbStationDao;
    private SbRouteStationDao sbRouteStationDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findById(Integer id) {
        return ResultWrapperUtil.setSuccessOf(sbStationDao.findById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper updateInfoById(SbStation sbStation) {
        sbStationDao.updateInfoById(sbStation);
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper delete(Integer id, Integer valid) {
        //更新station表
        sbStationDao.updateValidById(id, valid);
        //更新route_station表
        sbRouteStationDao.updateValidByStationId(id, valid);
        return ResultWrapperUtil.setSuccessOf(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultWrapper insert(SbStation sbStation) {
        sbStationDao.insert(sbStation);
        return ResultWrapperUtil.setSuccessOf(sbStationDao.findById(sbStation.getId()));
    }

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
    public void setSbRouteStationDao(SbRouteStationDao sbRouteStationDao) {
        this.sbRouteStationDao = sbRouteStationDao;
    }

    @Autowired
    public void setSbStationDao(SbStationDao sbStationDao) {
        this.sbStationDao = sbStationDao;
    }
}
