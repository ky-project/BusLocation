package com.ky.gps.service;

import com.ky.gps.entity.ResultWrapper;

/**
 * 站点表service层-接口
 * @author Daye
 */
public interface SbStationService {

    /**
     * 根据站点名模糊查询所有站点list
     * @param sbsStation 站点名
     * @return 返回json对象
     */
    ResultWrapper findFuzzyQueryBySbsStation(String sbsStation);

    /**
     * 查询所有站点信息
     * @return 返回json对象
     */
    ResultWrapper findAll();
}
