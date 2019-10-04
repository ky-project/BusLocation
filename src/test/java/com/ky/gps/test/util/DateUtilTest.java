package com.ky.gps.test.util;

import com.ky.gps.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;

public class DateUtilTest {

    @Test
    public void test01() throws ParseException {
        System.out.println(DateUtil.verifyHour("6:00", "6:00"));
    }
}
