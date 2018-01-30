package com.mingbang.mingbang.mingbang.utils;

import java.util.Calendar;

/**
 * TODO:获取某年某月的天数
 *
 * @author: zhaojy
 * @data:On 2018/1/23.
 */

public class GetDayOfMounthUtil {

    /**
     * TODO:获取某年某月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
