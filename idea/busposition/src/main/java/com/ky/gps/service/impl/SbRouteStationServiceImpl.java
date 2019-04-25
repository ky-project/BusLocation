package com.ky.gps.service.impl;

import com.ky.gps.dao.SbRouteStationDao;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.inter.SbRouteStationService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Daye
 * 路线对应站点Service接口的实现类
 */
@Service
public class SbRouteStationServiceImpl implements SbRouteStationService {

    @Resource
    private SbRouteStationDao sbRouteStationDao;

    @Override
    public ResultWrapper findAllRouteStation() {
        //创建结果封装对象
        ResultWrapper resultWrapper;
        //创建待存放的路线站点list
        List<Map<String, Object>> routeStationList = new ArrayList<>();
        //查询所有路线和站点信息
        List<Map<String, Object>> allRouteStationMapList = sbRouteStationDao.findAllRouteStation();
        //如果没有数据，则直接封装并返回结果对象
        if (0 == allRouteStationMapList.size()) {
            return ResultWrapperUtil.setSuccessOf(allRouteStationMapList);
        }
        //创建临时的路线名，初始化为第一条路线的name
        String routeName = allRouteStationMapList.get(0).get("routeName").toString();
        //创建临时的路线id，初始化为第一条路线的id
        Integer routeId = (Integer)allRouteStationMapList.get(0).get("routeId");
        //创建临时站点信息list
        List<Map<String, Object>> sortRouteStationList = new ArrayList<>();
        //keys={routeName, stationName, longitude, latitude, departTime}
        for (Map<String, Object> allRouteStationMap : allRouteStationMapList) {
            //判断路线名是否相同，若不同，则表示将要存放下一条路线的站点信息
            if (!routeName.equals(allRouteStationMap.get("routeName").toString())) {
                findAllRouteStationSortList(routeStationList, routeName, routeId, sortRouteStationList);
                //重置sortRouteStationList
                sortRouteStationList = new ArrayList<>();
                //对routeName进行重新赋值
                routeName = allRouteStationMap.get("routeName").toString();
                routeId = (Integer)allRouteStationMap.get("routeId");
            }
            //移除routeName字段
            allRouteStationMap.remove("routeName");
            allRouteStationMap.remove("routeId");
            //将该map存入list中
            sortRouteStationList.add(allRouteStationMap);
        }
        //处理最后一条记录
        findAllRouteStationSortList(routeStationList, routeName, routeId, sortRouteStationList);
        //创建一个待存入最终路线站点map
        resultWrapper = ResultWrapperUtil.setSuccessOf(routeStationList);
        return resultWrapper;
    }

    @Override
    public ResultWrapper findStationByRouteId(Integer routeId) {
        //获取该路线的所有站点信息
        List<Map<String, Object>> stationList = sbRouteStationDao.findStationByRouteId(routeId);
        //判断list的大小，两种特殊情况处理
        if (1 < stationList.size()) {
            //对stationList根据departTime进行排序
            sortByDepartTime(stationList);
            //路线存在两站及以上站点时，遍历list
            findAllRouteStationAddPrevAndNext(stationList);
        } else if (1 == stationList.size()) {
            //如果只有一个站点，则上一站下一站都为无
            Map<String, Object> station = stationList.get(0);
            station.put("prevStation", "无");
            station.put("nextStation", "无");
        }
        return ResultWrapperUtil.setSuccessOf(stationList);
    }

    /**
     * 将路线name和对应的站点list存入一个map中，并将该map存入待返回的结果对象
     *
     * @param routeStationList     最终汇总的路线站点list
     * @param routeName            路线名
     * @param routeId              路线id
     * @param sortRouteStationList 排序好的该路线的所有站点list
     */
    private void findAllRouteStationSortList(List<Map<String, Object>> routeStationList,
                                             String routeName,
                                             Integer routeId,
                                             List<Map<String, Object>> sortRouteStationList) {
        //创建一个待存入最终路线站点map
        Map<String, Object> routeStationTmpMap = new HashMap<>(16);
        //将路线名和id存入
        routeStationTmpMap.put("routeName", routeName);
        routeStationTmpMap.put("routeId", routeId);
        sortByDepartTime(sortRouteStationList);
        //设置前一站和后一站
        if (1 == sortRouteStationList.size()) {
            //如果只有一个站点，则上一站下一站都为无
            Map<String, Object> station = sortRouteStationList.get(0);
            station.put("prevStation", "无");
            station.put("nextStation", "无");
        } else {
            findAllRouteStationAddPrevAndNext(sortRouteStationList);
        }
        //将排序好的路线站点信息存入map
        routeStationTmpMap.put("stationInfo", sortRouteStationList);
        //将临时map加入最终结果中
        routeStationList.add(routeStationTmpMap);
    }

    /**
     * 根据departTime对list进行排序
     * @param sortRouteStationList 待排序的list
     */
    private void sortByDepartTime(List<Map<String, Object>> sortRouteStationList) {
        //根据departTime进行排序
        sortRouteStationList.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                //创建待格式化的日期的格式
                DateFormat dfHm = new SimpleDateFormat("HH:mm");
                try {
                    //格式化日期字符串
                    Date time1 = dfHm.parse(map1.get("departTime").toString());
                    Date time2 = dfHm.parse(map2.get("departTime").toString());
                    //对两个日期进行比较
                    if (time1.compareTo(time2) > 0) {
                        //大于为1
                        return 1;
                    } else if (time1.compareTo(time2) == 0) {
                        //相等为0
                        return 0;
                    } else {
                        //小于为-1
                        return -1;
                    }
                } catch (ParseException pe) {
                    //异常处理
                    pe.printStackTrace();
                    return -1;
                }
            }
        });
    }

    /**
     * 为该路线的所有站点设置上一站和下一站信息
     *
     * @param sortRouteStationList 待添加的路线对应站点list
     */
    private void findAllRouteStationAddPrevAndNext(List<Map<String, Object>> sortRouteStationList) {
        //路线存在两站及以上站点时，遍历list
        for (int index = 0; index < sortRouteStationList.size(); index++) {
            //获取当前index的station对象
            Map<String, Object> station = sortRouteStationList.get(index);
            //判断第一站、中间站、终点站
            if (index - 1 < 0) {
                //如果是第一站，则上一站为无
                station.put("prevStation", "无");
                //下一站为index+1的stationName
                station.put("nextStation", sortRouteStationList.get(index + 1).get("stationName"));
            } else if (index + 1 >= sortRouteStationList.size()) {
                //如果是终点站，则上一站位index-1的stationName
                station.put("prevStation", sortRouteStationList.get(index - 1).get("stationName"));
                //下一站为无
                station.put("nextStation", "无");
            } else {
                //如果为中间站,则上一站位index-1的stationName
                station.put("prevStation", sortRouteStationList.get(index - 1).get("stationName"));
                //下一站为index+1的stationName
                station.put("nextStation", sortRouteStationList.get(index + 1).get("stationName"));
            }
        }
    }

}
