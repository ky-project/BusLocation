package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbBusPositionHisService;
import com.ky.gps.util.ResultWrapperUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 历史定位管理
 *
 * @author Darren
 */
@Controller
@RequestMapping("/m/positionHis")
public class SbBusPositionHisManageHandler {

    @Autowired
    private SbBusPositionHisService sbBusPositionHisService;

    /**
     * 根据路线id和日期查询历史行驶定位
     */
    @PermissionName(displayName = "历史定位筛选", group = "历史定位管理")
    @RequiresPermissions("positionHis:fQuery")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper findByRouteIdAndDate(@RequestBody Map<String, Object> params,
                                              HttpServletResponse response,
                                              HttpServletRequest request) {
        ResultWrapper resultWrapper;
        if (null != params
                && !params.isEmpty()
                && params.get("routeId") != null
                && params.get("recodeTime") != null) {
            Integer routeId = (Integer) params.get("routeId");
            String recodeTime = params.get("recodeTime").toString();
            resultWrapper = ResultWrapperUtil.setSuccessOf(sbBusPositionHisService.findByRouteIdAndCreatedDate(routeId, recodeTime));
        } else{
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR,response);
        }
        return resultWrapper;
    }
}
