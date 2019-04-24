package com.ky.gps.dao;

import com.ky.gps.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Daye
 * 部门类的dao类
 */
@Repository
public interface DepartmentDao {

    /**
     * 根据id查询部门name
     * @param id 部门id
     * @return 将name封装到对象中返回
     */
    Department findNameById(@Param("id") Integer id);
}
