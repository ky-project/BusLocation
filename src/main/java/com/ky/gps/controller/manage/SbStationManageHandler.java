package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbStation;
import com.ky.gps.service.SbStationService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.StringUtil;
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
 * 站点处理器
 *
 * @author Daye
 */
@Controller
@RequestMapping("/m/station")
public class SbStationManageHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SbStationManageHandler.class);

    private SbStationService sbStationService;

    /**
     * 根据站点名模糊查询所有站点基本信息
     *
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "站点筛选", group = "站点管理")
    @RequiresPermissions("station:fQuery")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultWrapper getFuzzyQuery(@RequestBody(required = false) Map<String, Object> params,
                                       HttpServletResponse response,
                                       HttpServletRequest request) {
        ResultWrapper resultWrapper;
        if (params == null || params.size() < 1 || !params.containsKey("sbsStation")) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        } else {
            Object sbsStationObj = params.get("sbsStation");
            //判断参数是否有效
            if (sbsStationObj == null) {
                resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
            } else {
                resultWrapper = sbStationService.findFuzzyQueryBySbsStation("%" + sbsStationObj.toString() + "%");
            }
        }
        return resultWrapper;
    }

    /**
     * 查询所有站点基本信息
     *
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "站点查询", group = "站点管理")
    @RequiresPermissions("station:query")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultWrapper getAll() {
        return sbStationService.findAll();
    }

    @Autowired
    public void setSbStationService(SbStationService sbStationService) {
        this.sbStationService = sbStationService;
    }
}
