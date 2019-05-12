package com.ky.gps.service.impl;

import com.ky.gps.dao.DepartmentDao;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.DepartmentService;
import com.ky.gps.util.ResultWrapperUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Daye
 * 部门服务层Service接口实现类
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public ResultWrapper findNameById(Integer id) {
        //查询id对应的name
        String name = departmentDao.findNameById(id);
        //将name封装到json对象中返回
        return ResultWrapperUtil.setSuccessOf(name);
    }
}
