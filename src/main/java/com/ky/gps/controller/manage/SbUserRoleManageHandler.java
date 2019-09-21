package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SbUserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户角色管理
 * @author Daye
 */
@Controller
@RequestMapping("/m/userRole")
public class SbUserRoleManageHandler {

    private SbUserRoleService sbUserRoleService;

    /**
     * 查询所有用户的基本信息和其所拥有的所有权限
     * @return 返回json格式数据
     */
    @PermissionName(displayName = "用户角色查询", group = "用户角色管理")
    @RequiresPermissions("userRole:query")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultWrapper findAllUserAndRoles(){
        ResultWrapper resultWrapper = sbUserRoleService.findAllUserAndRole();
        return resultWrapper;
    }

    @Autowired
    public void setSbUserRoleService(SbUserRoleService sbUserRoleService) {
        this.sbUserRoleService = sbUserRoleService;
    }
}
