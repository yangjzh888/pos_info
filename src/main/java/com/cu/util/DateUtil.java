package com.cu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtil {

    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

    public static String getYesterday(Timestamp timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        return sdfDate.format(yesterday);
    }

}
