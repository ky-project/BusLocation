package com.ky.gps.service.inter;

import com.ky.gps.entity.ResultWrapper;

/**
 * @author Daye
 *
 * 部门服务层Service接口
 */
public interface DepartmentService {

    /**
     * 根据id查询部门name
     * @param id 部门id
     * @return 封装name值到json对象
     */
    ResultWrapper findNameById(Integer id);
}
