package com.gps.service.inter;

import com.gps.entity.Teacher;

import java.util.Map;

/**
 * @author Daye
 */
public interface TeacherService {
    /**
     * 根据教工号，密码来验证教师登录信息
     * @param map 存放教工号、密码的键值对
     * @return 返回查找到的数据，封装进Teacher中
     */
    Teacher findByJobNumberAndPassword(Map<String, Object> map);
}
