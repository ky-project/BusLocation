package com.gps.service.impl;

import com.gps.dao.TeacherDao;
import com.gps.entity.Teacher;
import com.gps.service.inter.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Daye
 * 教师Service类，单例
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Teacher findByJobNumberAndPassword(Map<String, Object> map) {
        return teacherDao.findByJobNumberAndPassword(map);
    }
}
