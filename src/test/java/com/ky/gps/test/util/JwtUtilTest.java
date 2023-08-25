package com.ky.gps.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ky.gps.entity.SysUser;
import com.ky.gps.util.JwtUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilTest {

    @Test
    public void test02(){
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", null);
        System.out.println(map.containsKey("startDate"));
        System.out.println(map.get("startDate").toString());
    }

    @Test
    public void test01() throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        SysUser user = new SysUser();
        user.setId(28);
        user.setIdCard("jkasdjknqkw");
        user.setPassword("asdds");
        user.setEmail("123@123.com");
        String jsonUser = map.writeValueAsString(user);
        String sign = JwtUtil.sign(jsonUser);

        System.out.println(sign);
        System.out.println(JwtUtil.verify(sign));
    }
}
