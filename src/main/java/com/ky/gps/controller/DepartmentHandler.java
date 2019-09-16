package com.ky.gps.controller;

import com.ky.gps.entity.ErrorCode;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.DepartmentService;
import com.ky.gps.util.ResultWrapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Daye
 * 部门处理器
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentHandler {
    /**
     * 日志打印对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentHandler.class);

    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/base/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultWrapper findAllIdAndName(){
        ResultWrapper resultWrapper;
        try{
            resultWrapper = departmentService.findAllNameAndId();
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            resultWrapper = ResultWrapperUtil.setErrorOf(ErrorCode.SYSTEM_ERROR);
        }
        return resultWrapper;
    }
}
