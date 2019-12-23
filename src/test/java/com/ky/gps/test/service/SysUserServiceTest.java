package com.ky.gps.test.service;

import com.ky.gps.entity.Department;
import com.ky.gps.entity.ResultWrapper;
import com.ky.gps.entity.SysUser;
import com.ky.gps.service.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysUserServiceTest {
    private SysUserService sysUserService;


    @Before
    public void init(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sysUserService = applicationContext.getBean(SysUserService.class);
    }

    @Test
    public void test07(){
        SysUser sysUser = new SysUser();
        sysUser.setEmail("123@123.com");
        sysUser.setPassword("123");
        sysUser.setSalt("123");
        sysUser.setIdCard("123456798");
        sysUser.setAccountDate(new Date());
        Department department = new Department();
        department.setId(5);
        sysUser.setDepartment(department);
        sysUser.setWorkId("20190917");
        sysUser.setRealName("tmp");
        ResultWrapper resultWrapper = sysUserService.saveUserBaseInfo(sysUser);
        System.out.println(resultWrapper.getData());
    }

    @Test
    public void test06(){
        Map<String, Object> params = new HashMap<>();
        params.put("realName", "%陆%");
        params.put("workId", "%Xb%");
        params.put("departmentId", 1);
        sysUserService.findBaseInfoLikeRealNameAndWorkIdAndDepartment(params);
    }

    @Test
    public void test05(){
        ResultWrapper userList = sysUserService.findUserByDepartmentId(1, 0, 5);
        System.out.println(userList);
    }

    @Test
    public void test04(){
        ResultWrapper userList = sysUserService.findUserByRealNameFuzzyPages("陆", 0, 5);
        System.out.println(userList);
    }

    @Test
    public void test03(){
        ResultWrapper userList = sysUserService.findUserByWorkIdFuzzyPages("Xb", 0, 5);
        System.out.println(userList);
    }

    @Test
    public void test02(){
        ResultWrapper userList = sysUserService.findUserList();
        System.out.println(userList);
        if(null == userList.getData()){
            System.out.println("error");
        } else{
            System.out.println("yes");
        }
    }

    @Test
    public void test01(){
        Map<String, Object> map = new HashMap<>(16);
        map.put("userName", "admin");
        map.put("password", "admin");
        Map<String, Object> baseInfo = sysUserService.simpleUserLogin(map);
        System.out.println(baseInfo);

    }

}

