package com.ky.gps.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tangqq, Xu
 */
public class JudgeTimeUtil {
    /**
     * 中文的星期
     */
    private static final String[] WEEK_CN = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 判断Timestamp类型的时间是否在指定时间段内
     * @param time yyyy-MM-dd HH:mm:ss
     * @param startTime HH:mm
     * @param endTime HH:mm
     * @return true or false
     */
    public static boolean isEffectiveTimestamp(Timestamp time, String startTime, String endTime){
        //确定时间格式-时分
        DateFormat dfHm = new SimpleDateFormat("HH:mm");
        //确定时间格式-时分
        DateFormat dfYmd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //判断年月日是否是今天的日期
            if(!dfYmd.format(time).equals(dfYmd.format(new Date()))){
                return false;
            }
            //格式化待判定的时间，时分
            String timeStr = dfHm.format(time);
            //将String类型根据格式转为date类型
            Date timeNow = dfHm.parse(timeStr);
            Date start = dfHm.parse(startTime);
            Date end = dfHm.parse(endTime);
            //判断是否在时间段内
            if(timeNow.compareTo(start) >= 0 && timeNow.compareTo(end) <= 0) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前时间是否在指定时间范围内
     *
     * @param startTime 开始时间 HH:mm
     * @param endTime   结束时间 HH:mm
     * @return 是：true,否:false
     */
    public static boolean isEffectiveDate(String startTime, String endTime) {
        try {
            Calendar cal = Calendar.getInstance();
            //24小时制
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            //获取当下时间
            String nowTime = dateFormat.format(cal.getTime());
//            System.out.println("星期：" + week_int + " " + dateFormat.format(cal.getTime()));//可测试
            if (nowTime.equals(startTime)
                    || nowTime.equals(endTime)) {
                return true;
            }//if 判断时间与始末相等
            if ((dateFormat.parse(nowTime)).after(dateFormat.parse(startTime))
                    && (dateFormat.parse(nowTime)).before(dateFormat.parse(endTime))) {
                return true;
            }//if 判断时间在一定范围内

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }//isEffectiveDate

    /**
     * 获取今天的星期名
     *
     * @return 星期名
     */
    public static String getWeek() {
        return WEEK_CN[(Calendar.getInstance()).get(Calendar.DAY_OF_WEEK) - 1];
    }
}
