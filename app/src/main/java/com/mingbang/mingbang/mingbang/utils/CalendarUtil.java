package com.mingbang.mingbang.mingbang.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class CalendarUtil {
    private static Calendar c = Calendar.getInstance();

    private static SimpleDateFormat formatter;
    private static Date curDate;

    /**
     * TODO:获取年份
     */
    public static int getYear() {
        return c.get(Calendar.YEAR);
    }

    /**
     * TODO：获取月份
     */
    public static int getMonth() {
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * TODO：获取日期
     */
    public static int getDay() {
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * TODO:获取当前日期
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDate() {
        formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
}
