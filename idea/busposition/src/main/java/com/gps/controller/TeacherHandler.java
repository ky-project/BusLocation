package com.gps.controller;

import com.gps.entity.Teacher;
import com.gps.service.inter.TeacherService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daye
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/teacher")
public class TeacherHandler {

    @Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Teacher loginCheck(Teacher teacher){
        Map<String, Object> map = new HashMap<>(16);
        map.put("JobNumber", teacher.getJobNumber());
        map.put("Password", teacher.getPassword());
        System.out.println(teacher);
        Teacher checkTeacher = teacherService.findByJobNumberAndPassword(map);
        return checkTeacher;
    }
}
