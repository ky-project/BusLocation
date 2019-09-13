package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRouteDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRoute;
import com.ky.gps.service.SbRouteService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 路线实体类的Service接口实现类
 */
@Service
public class SbRouteServiceImpl implements SbRouteService {

    @Resource
    private SbRouteDao sbRouteDao;

//    @Cacheable(value = "route_base_info", key = "#id")
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

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public List<Map<String, Object>> findAllBaseInfo() {
        //TODO 判断当前时间是否在开始结束时间内
        return sbRouteDao.findAllBaseInfo();
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findAllIdAndName() {
        //创建待返回的结果对象
        ResultWrapper resultWrapper = null;
        //接收查询的结果集合
        List<Map<String, Object>> sbRouteList = sbRouteDao.findAllIdAndName();
        //将结果集合存入结果对象中
        resultWrapper = ResultWrapperUtil.setSuccessOf(sbRouteList);
        return resultWrapper;
    }
}
