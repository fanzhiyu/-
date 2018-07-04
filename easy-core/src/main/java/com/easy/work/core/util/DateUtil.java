package com.easy.work.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Luopan
 * @time 2017/12/26.
 */
public class DateUtil {

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");

    /**
     * 获取两个时间相差月份
     * @param startDate 较小的时间
     * @param endDate 较大的时间
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(String startDate, String endDate){
        Date start = null;
        Date end = null;
        try {
            start = formatter.parse(startDate);
            end = formatter.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year2 - year1;
        // 获取月份的差值
        int monthInterval = month2 - month1 + 1;
        return yearInterval * 12 + monthInterval;
    }
}
