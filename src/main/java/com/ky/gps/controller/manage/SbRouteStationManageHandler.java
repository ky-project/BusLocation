package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbRouteStation;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.util.IntegerUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 路线站点管理
 *
 * @author Darren
 */
@Controller
@RequestMapping("/m/routeStation")
public class SbRouteStationManageHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(SbRouteStationManageHandler.class);

    @Autowired
    private SbRouteStationService sbRouteStationService;

    /**
     * 根据路线id查询该路线的所有站点信息
     * @return data为所有站点信息list
     */
    @PermissionName(displayName = "路线站点查询", group = "路线站点管理")
    @RequiresPermissions("routeStation:query")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultWrapper list(@RequestBody(required = false) Map<String, Object> params,
                              HttpServletRequest request,
                              HttpServletResponse response){
        ResultWrapper resultWrapper;
        //空值判断
        if (params != null
                && params.get("routeId") != null
                && IntegerUtil.isValid((Integer) params.get("routeId"))){
            //提取参数
            Integer routeId = (Integer) params.get("routeId");
            resultWrapper = ResultWrapperUtil.setSuccessOf(sbRouteStationService.findByRouteId(routeId));
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "id不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 根据路线id和站点id更新记录
     *
     * @param params 待删除的路线站点map
     * @return data为更新后的路线站点信息
     */
    @PermissionName(displayName = "路线站点更新", group = "路线站点管理")
    @RequiresPermissions("routeStation:update")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultWrapper update(@RequestBody(required = false) Map<String, Object> params,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //空值判断
        if (params != null
                && params.get("routeId") != null
                && params.get("stationId") != null
                && IntegerUtil.isValid((Integer) params.get("routeId"))
                && IntegerUtil.isValid((Integer) params.get("stationId"))) {
            //提取参数
            Integer routeId = (Integer) params.get("routeId");
            Integer stationId = (Integer) params.get("stationId");
            String sbsDepartTime = params.get("sbsDepartTime") != null ? params.get("sbsDepartTime").toString() : "";
            resultWrapper = ResultWrapperUtil.setSuccessOf(sbRouteStationService.updateDepartTimeByRouteIdAndStationId(stationId, routeId, sbsDepartTime));
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "需修改的id不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 根据路线id和站点id删除记录
     *
     * @param params 待删除的路线站点map
     * @return data为null
     */
    @PermissionName(displayName = "路线站点删除", group = "路线站点管理")
    @RequiresPermissions("routeStation:delete")
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResultWrapper delete(@RequestBody(required = false) Map<String, Object> params,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //空值判断
        if (params != null
                && params.get("routeId") != null
                && params.get("stationId") != null
                && IntegerUtil.isValid((Integer) params.get("routeId"))
                && IntegerUtil.isValid((Integer) params.get("stationId"))) {
            //提取参数
            Integer routeId = (Integer) params.get("routeId");
            Integer stationId = (Integer) params.get("stationId");
            sbRouteStationService.delete(stationId, routeId);
            resultWrapper = ResultWrapperUtil.setSuccessOf(null);
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "需绑定id不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 添加路线站点信息
     *
     * @param params 待添加的路线站点map
     * @return 返回添加完后的站点信息
     */
    @PermissionName(displayName = "路线站点绑定", group = "路线站点管理")
    @RequiresPermissions("routeStation:add")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultWrapper add(@RequestBody(required = false) Map<String, Object> params,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //空值判断
        if (params != null
                && params.get("routeId") != null
                && params.get("stationId") != null
                && IntegerUtil.isValid((Integer) params.get("routeId"))
                && IntegerUtil.isValid((Integer) params.get("stationId"))) {
            //提取参数
            Integer routeId = (Integer) params.get("routeId");
            Integer stationId = (Integer) params.get("stationId");
            String sbsDepartTime = params.get("sbsDepartTime") != null ? params.get("sbsDepartTime").toString() : "";
            //判断是否重复绑定
            if (sbRouteStationService.findByRouteIdAndStationId(routeId, stationId) != null) {
                resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SAVE_ERROR, "无法重复绑定", response);
            } else {
                resultWrapper = ResultWrapperUtil.setSuccessOf(sbRouteStationService.save(stationId, routeId, sbsDepartTime));
            }
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "需绑定id不可为空", response);
        }
        return resultWrapper;
    }
}
