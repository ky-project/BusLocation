package com.ky.gps.controller.manage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色管理
 *
 * @author Daye
 */
@Controller
@RequestMapping("/m/role")
public class SysRoleManageHandler {

    private SysRoleService sysRoleService;

    /**
     * 查询所有角色信息
     *
     * @return 返回json格式数据
     */
//    @RequiresPermissions("role:query")
//    @PermissionName(group = "角色管理", displayName = "查询角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper findAllRole() {
        ResultWrapper resultWrapper = sysRoleService.findAllRole();
        return resultWrapper;
    }

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }
}
