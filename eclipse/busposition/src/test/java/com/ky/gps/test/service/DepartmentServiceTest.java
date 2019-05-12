package com.ky.gps.test.service;

import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DepartmentServiceTest {
    private ApplicationContext applicationContext;
    private DepartmentService departmentService;

    @Before
    public void init(){
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        departmentService = applicationContext.getBean(DepartmentService.class);
    }

    @Test
    public void test01(){
        ResultWrapper nameResult = departmentService.findNameById(1);
        System.out.println(nameResult);
        if(nameResult.getData() == null){
            System.out.println("true");
        }
    }
}
