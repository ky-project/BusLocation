package com.ky.gps.controller.manage;

import com.ky.gps.entity.ResultWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 角色权限管理处理器
 * @author Daye
 */
@RequestMapping("/m/roleAuthority")
@Controller
public class SbRoleAuthorityManageHandler {

    /**
     * 添加角色的操作权限
     * @return 返回json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultWrapper addRecording(@RequestBody Map<String, Object> params,
                                      HttpServletResponse response,
                                      HttpServletRequest request){
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        return null;
    }
}
