package com.ky.gps.controller;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysUser;
import com.ky.gps.service.SbBusPositionService;
import com.ky.gps.service.SbRouteService;
import com.ky.gps.service.SbRouteStationService;
import com.ky.gps.service.SbUserBusService;
import com.ky.gps.util.ResultWrapperUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;
import java.util.Map;

/**
 * @author Daye
 */
@Controller
@RequestMapping(value = "/route")
public class SbRouteHandler {
    /**
     * 日志打印对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SbRouteHandler.class);

    @Resource
    private SbRouteService sbRouteService;
    @Resource
    private SbRouteStationService sbRouteStationService;
    @Autowired
    private SbUserBusService sbUserBusService;
    @Resource
    private SbBusPositionService sbBusPositionService;

    /**
     * 查询用户能查看的所有路线id和name
     */
    @SuppressWarnings("unchecked")
    @PermissionName(displayName = "路线查询", group = "路线管理")
    @RequiresPermissions("route:query")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultWrapper list(){
        //获取当前用户信息
        Map<String, Object> user = (Map<String, Object>) SecurityUtils.getSubject().getPrincipal();
        Integer id = (Integer) user.get("id");
        //TODO 用户只能查看自己所能查看的路线信息
//        ResultWrapper resultWrapper = ResultWrapperUtil.setSuccessOf(sbUserBusService.findRouteIdAndRouteNameByUserId(id));
        ResultWrapper resultWrapper = sbRouteService.findAllIdAndName();
        return resultWrapper;
    }

    /**
     * 根据路线id查询该路线的校车所有定位信息
     * 左闭右开
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/one/position", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findRoutePositionByRouteIdAndStartIndex(@RequestParam(value = "routeId") Integer routeId,
                                                                 @RequestParam(value = "startIndex") Integer startIndex,
                                                                 HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //检验参数是否合法
        if (null == routeId || 0 >= routeId
                || null == startIndex || 0 > startIndex) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SELECT_ERROR, "参数不合法,startIndex>0,startIndex>=0", response);
        } else {
            //接收service层封装好的json对象
            resultWrapper = sbBusPositionService.findAllEffectiveRoutePositionByRouteId(routeId, startIndex);
        }
        return resultWrapper;
    }


    /**
     * 根据路线id查询该路线的所有站点信息
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/station/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findRouteStationByRouteId(@RequestParam(value = "routeId") Integer routeId,
                                                   HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //判断routeId是否合法
        if (null == routeId || 0 >= routeId) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.SELECT_ERROR, "routeId>0", response);
        } else {
            //获取站点信息
            resultWrapper = sbRouteStationService.findStationByRouteId(routeId);
        }
        return resultWrapper;
    }

    /**
     * 查询路线的id和name
     *
     * @return 返回json格式数据
     */
    @RequestMapping(value = "/base/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper findRouteNameAndId() {
        ResultWrapper resultWrapper;
        //获得封装对象
        resultWrapper = sbRouteService.findAllIdAndName();
        return resultWrapper;
    }
}
