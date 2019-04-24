package com.ky.gps.service.impl;

import com.ky.gps.dao.SbBusPositionDao;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.service.inter.SbBusPositionService;
import com.ky.gps.util.JudgeTimeUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public ResultWrapper findAllEffectiveRoutePosition() {
        //创建待返回的封装数据对象
        ResultWrapper resultWrapper;
        //创建待返回的数据类型，将过滤好的数据存入该数据类型
        List<Map<String, Object>> effectiveRoutePosition = new ArrayList<>();
        try {
            //获取该星期的所有校车定位数据
            List<Map<String, Object>> allRoutePosition = sbBusPositionDao.findAllRoutePosition(JudgeTimeUtil.getWeek());
            if (0 == allRoutePosition.size()) {
                return ResultWrapperUtil.setSuccessOf(allRoutePosition);
            }//判断是否有记录
            //创建临时存放站点的定位信息，存放每个站点的所有定位信息存入该list
            List<Map<String, Object>> filterTmpPositionList = new ArrayList<>();
            //临时站点名,初始化为第一条数据的站点名
            String routeNameTmp = allRoutePosition.get(0).get("routeName").toString();
            //遍历筛选，Keys={routeName, startTime, endTime, recodeTime, longitude, latitude, velocity, direction}
            for (int i = 0; i < allRoutePosition.size(); i++) {
                //获取map
                Map<String, Object> routePositionMap = allRoutePosition.get(i);
                //判断当前时间是否在路线时间段内
//                if (!JudgeTimeUtil.isEffectiveDate(routePositionMap.get("startTime").toString().trim(),
//                        routePositionMap.get("endTime").toString().trim())
//                        || !JudgeTimeUtil.isEffectiveTimestamp((Timestamp) routePositionMap.get("recodeTime"),
//                        routePositionMap.get("startTime").toString().trim(),
//                        routePositionMap.get("endTime").toString().trim())) {
//                    //如果当前时间不在该时间段，或者定位信息的时间段不在路线时间段内，移除该记录
//                    allRoutePosition.remove(routePositionMap);
//                    i--;
//
//                } else {
                //如果在该时间段，删除不需要的数据
                routePositionMap.remove("startTime");
                routePositionMap.remove("endTime");
                routePositionMap.remove("recodeTime");
                //判断站点名是否已经被记录下来
                if (!routePositionMap.get("routeName").toString().equals(routeNameTmp)) {
                    //如果未被记录下来，说明开始新的站点定位数据的记录
                    //创建存放站点的所有站点和站点名Map
                    Map<String, Object> filterTmpRouteMap = new HashMap<>(16);
                    //将站点名记录保存
                    filterTmpRouteMap.put("routeName", routeNameTmp);
                    //将该站点的所有定位信息保存下来
                    filterTmpRouteMap.put("trackRoute", filterTmpPositionList);
                    //将该map存入总的list中
                    effectiveRoutePosition.add(filterTmpRouteMap);
                    //重置routeName
                    routeNameTmp = routePositionMap.get("routeName").toString();
                    //清空前一个站点的所有定位
                    filterTmpPositionList = new ArrayList<>();
                }
                routePositionMap.remove("routeName");
                //然后将本定位信息存入临时站点list
                filterTmpPositionList.add(routePositionMap);

//                }
            }
            //如果全部站定位数据都不符合时间段，则时间返回空list
            if (0 == allRoutePosition.size()) {
                return ResultWrapperUtil.setSuccessOf(allRoutePosition);
            }
            //保存最后一组记录
            Map<String, Object> filterTmpRouteMap = new HashMap<>(16);
            //将站点名记录保存
            filterTmpRouteMap.put("routeName", routeNameTmp);
            //将该站点的所有定位信息保存下来
            filterTmpRouteMap.put("trackRoute", filterTmpPositionList);
            //将该map存入总的list中
            effectiveRoutePosition.add(filterTmpRouteMap);
            //将最后的有效路线定位存入结果对象中
            resultWrapper = ResultWrapperUtil.setSuccessOf(effectiveRoutePosition);
        } catch (Exception e) {
            //异常处理
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }
}
