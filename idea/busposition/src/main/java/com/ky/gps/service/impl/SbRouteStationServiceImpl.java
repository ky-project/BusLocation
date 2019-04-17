package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRouteDao;
import com.ky.gps.dao.SbRouteStationDao;
import com.ky.gps.service.inter.SbRouteStationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Daye
 * 路线对应站点Service接口的实现类
 */
@Service
public class SbRouteStationServiceImpl implements SbRouteStationService {

    @Resource
    private SbRouteStationDao sbRouteStationDao;

    @Override
    public List<Map<String, Object>> findStationByRouteId(Integer routeId) {
        //获取该路线的所有站点信息
        List<Map<String, Object>> stationList = sbRouteStationDao.findStationByRouteId(routeId);
        //判断list的大小，两种特殊情况处理
        if (0 == stationList.size()) {
            //如果只有0，说明无站点，直接返回即可
            return stationList;
        } else if (1 == stationList.size()) {
            //如果只有一个站点，则上一站下一站都为无
            Map<String, Object> station = stationList.get(0);
            station.put("prevStation", "无");
            station.put("nextStation", "无");
            //返回设置好后的list
            return stationList;
        }
        //路线存在两站及以上站点时，遍历list
        for (int index = 0; index < stationList.size(); index++) {
            //获取当前index的station对象
            Map<String, Object> station = stationList.get(index);
            //判断第一站、中间站、终点站
            if (index - 1 < 0) {
                //如果是第一站，则上一站为无
                station.put("prevStation", "无");
                //下一站为index+1的stationName
                station.put("nextStation", stationList.get(index + 1).get("stationName"));
            } else if (index + 1 >= stationList.size()) {
                //如果是终点站，则上一站位index-1的stationName
                station.put("prevStation", stationList.get(index - 1).get("stationName"));
                //下一站为无
                station.put("nextStation", "无");
            } else{
                //如果为中间站,则上一站位index-1的stationName
                station.put("prevStation", stationList.get(index - 1).get("stationName"));
                //下一站为index+1的stationName
                station.put("nextStation", stationList.get(index + 1).get("stationName"));
            }
        }
        return stationList;
    }
}
