package com.ky.gps.test.util;

import com.ky.gps.util.JudgeTimeUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;

public class JudgeTimeTest {

    @Test
    public void test01() throws ParseException {
        String time = "2019-04-20 06:00:00";
        Timestamp timestamp = Timestamp.valueOf(time);
        System.out.println(timestamp);
        System.out.println(JudgeTimeUtil.isEffectiveTimestamp(timestamp,"6:00", "24:00"));


    }
}
