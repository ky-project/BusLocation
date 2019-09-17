package com.ky.gps.controller.manage;

import com.ky.gps.annotation.PermissionName;
import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.SysRoleService;
import com.ky.gps.util.IntegerUtil;
import com.ky.gps.util.ResultWrapperUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
     * 根据id恢复角色
     * @param params 存放json提交的参数map，包含
     *               id 角色id
     * @return 返回json数据
     */
    @RequiresPermissions("role:rollback")
    @PermissionName(displayName = "角色恢复", group = "角色管理")
    @RequestMapping(value = "/rollback", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper rollbackRoleById(@RequestBody Map<String, Object> params,
                                        HttpServletResponse response){
        Integer id = (Integer) params.get("id");
        ResultWrapper resultWrapper;
        if(IntegerUtil.isValid(id)){
            resultWrapper = sysRoleService.rollbackById(id);
        } else{
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        }
        return resultWrapper;
    }

    /**
     * 根据id删除角色
     * @param params 存放json提交的参数map，包含
     *               id 角色id
     * @return 返回json数据
     */
    @RequiresPermissions("role:delete")
    @PermissionName(displayName = "角色删除", group = "角色管理")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultWrapper deleteRoleById(@RequestBody Map<String, Object> params,
                                        HttpServletResponse response){
        Integer id = (Integer) params.get("id");
        ResultWrapper resultWrapper;
        if(IntegerUtil.isValid(id)){
            resultWrapper = sysRoleService.deleteById(id);
        } else{
            resultWrapper = ResultWrapperUtil.setErrorAndStatusOf(ErrorCode.EMPTY_ERROR, response);
        }
        return resultWrapper;
    }

    /**
     * 查询所有角色信息
     *
     * @return 返回json格式数据
     */
    @RequiresPermissions("role:query")
    @PermissionName(group = "角色管理", displayName = "角色查询")
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
