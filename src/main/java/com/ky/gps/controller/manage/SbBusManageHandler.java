package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SbBus;
import com.ky.gps.service.SbBusService;
import com.ky.gps.util.IntegerUtil;
import com.ky.gps.util.ResultWrapperUtil;
import com.ky.gps.util.SbBusUtil;
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
 * 校车管理
 *
 * @author Darren
 */
@Controller
@RequestMapping("/m/bus")
public class SbBusManageHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(SbBusManageHandler.class);

    @Autowired
    private SbBusService sbBusService;

    /**
     * 插入校车记录
     * @param sbBus 待插入的校车记录
     * @return 返回插入的校车记录信息
     */
    @PermissionName(displayName = "校车添加", group = "校车管理")
    @RequiresPermissions("bus:add")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultWrapper save(@RequestBody(required = false) SbBus sbBus,
                             HttpServletRequest request,
                             HttpServletResponse response){
        ResultWrapper resultWrapper;
        if (SbBusUtil.verifyBusExcludeId(sbBus)) {
            resultWrapper = ResultWrapperUtil.setSuccessOf(sbBusService.insert(sbBus));
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "基本信息不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 根据id删除校车
     *
     * @param params 参数，包含id
     * @return 返回json对象，data为null
     */
    @PermissionName(displayName = "校车删除", group = "校车管理")
    @RequiresPermissions("bus:delete")
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResultWrapper delete(@RequestBody(required = false) Map<String, Object> params,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        if (params != null
                && params.containsKey("id")
                && params.get("id") != null
                && IntegerUtil.isValid((Integer) params.get("id"))) {
            Integer id = (Integer) params.get("id");
            sbBusService.delete(id);
            resultWrapper = ResultWrapperUtil.setSuccessOf(null);
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "id不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 根据id更新校车
     *
     * @param sbBus 校车对象
     * @return 返回更新后的校车信息
     */
    @PermissionName(displayName = "校车更新", group = "校车管理")
    @RequiresPermissions("bus:update")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultWrapper update(@RequestBody(required = false) SbBus sbBus,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ResultWrapper resultWrapper;
        if (SbBusUtil.verifyBus(sbBus)) {
            resultWrapper = ResultWrapperUtil.setSuccessOf(sbBusService.update(sbBus));
        } else {
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, "基本信息不可为空", response);
        }
        return resultWrapper;
    }

    /**
     * 查询所有校车信息
     *
     * @return 返回json，包含校车list
     */
    @PermissionName(displayName = "校车查询", group = "校车管理")
    @RequiresPermissions("bus:query")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper list() {
        ResultWrapper resultWrapper;
        resultWrapper = ResultWrapperUtil.setSuccessOf(sbBusService.find());
        return resultWrapper;
    }

}
