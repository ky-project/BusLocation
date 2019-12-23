package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SysLogService;
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
 * @author Darren
 */
@Controller
@RequestMapping("/m/log")
public class SysLogManageHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysLogManageHandler.class);

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询日志信息
     * 根据用户number、name、operator模糊查询记录
     * 根据日期精确查询指定日期记录
     */
    @PermissionName(displayName = "日志筛选", group = "日志管理")
    @RequiresPermissions("log:fQuery")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultWrapper list(@RequestBody(required = false) Map<String, Object> params,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ResultWrapper resultWrapper;
        String userNumber = "";
        String name = "";
        String operator = "";
        String createdDate = "";
        if (params != null && !params.isEmpty()) {
            if (params.get("userNumber") != null) {
                userNumber = "%" + params.get("userNumber").toString() + "%";
            }
            if (params.get("name") != null) {
                name = "%" + params.get("name").toString() + "%";
            }
            if (params.get("operator") != null) {
                operator = "%" + params.get("operator").toString() + "%";
            }
            if (params.get("createdDate") != null) {
                createdDate = params.get("createdDate").toString();
            }
        }
        resultWrapper = ResultWrapperUtil.setSuccessOf(sysLogService.findByUserAndNameAndOperatorAndCreatedDate(userNumber, name, operator, createdDate));
        return resultWrapper;
    }
}
