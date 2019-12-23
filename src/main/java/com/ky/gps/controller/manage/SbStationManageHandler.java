package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbStation;
import com.ky.gps.service.SbStationService;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SbStationUtil;
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
     * 获取所有站点名
     *
     * @return 返回所有站点名
     */
    @PermissionName(displayName = "站点查询", group = "站点管理")
    @RequiresPermissions("station:query")
    @ResponseBody
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public ResultWrapper findStationNames() {
        ResultWrapper resultWrapper;
        resultWrapper = ResultWrapperUtil.setSuccessOf(sbStationService.findNames());
        return resultWrapper;
    }

    /**
     * 更新站点信息
     *
     * @param sbStation 待更新的站点对象
     * @param request   请求域
     * @param response  响应域
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "站点更新", group = "站点管理")
    @RequiresPermissions("station:update")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultWrapper update(@RequestBody(required = false) SbStation sbStation,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        if (sbStation == null
                || !SbStationUtil.verityContainId(sbStation)
                || sbStationService.findById(sbStation.getId()).getData() == null) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "参数为空/id不存在", response);
        } else {
            resultWrapper = sbStationService.updateInfoById(sbStation);
        }
        return resultWrapper;
    }

    /**
     * 根据id删除对应的站点信息
     *
     * @param params   参数map,包含
     *                 id 站点id
     * @param response 响应域
     * @param request  请求域
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "站点删除", group = "站点管理")
    @RequiresPermissions("station:delete")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper delete(@RequestBody(required = false) Map<String, Object> params,
                                HttpServletResponse response,
                                HttpServletRequest request) {
        ResultWrapper resultWrapper;
        if (params == null || !params.containsKey("id") || params.get("id") == null) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        } else {
            Integer id = (Integer) params.get("id");
            resultWrapper = sbStationService.delete(id, 0);
        }
        return resultWrapper;
    }

    /**
     * 添加站点记录
     *
     * @param sbStation 站点对象
     * @param request   请求域
     * @param response  响应域
     * @return 返回json对象
     */
    @PermissionName(displayName = "站点添加", group = "站点管理")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresPermissions("station:add")
    @ResponseBody
    public ResultWrapper insert(@RequestBody(required = false) SbStation sbStation,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        //参数校验
        if (sbStation == null || !SbStationUtil.verity(sbStation)) {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        } else {
            resultWrapper = sbStationService.insert(sbStation);
        }
        return resultWrapper;
    }

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
