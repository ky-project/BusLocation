package com.gps.test;

import com.gps.service.inter.TeacherService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TeacherServiceTest {
    private ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");

    @Test
    public void test01(){
        TeacherService teacherService =applicationContext.getBean(TeacherService.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("JobNumber", "20112238");
        map.put("Password", "123");
        System.out.println(teacherService.findByJobNumberAndPassword(map));
    }
}
