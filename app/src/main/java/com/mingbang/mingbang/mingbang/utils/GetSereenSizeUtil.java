package com.mingbang.mingbang.mingbang.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author: zhaojy
 * @data:On 2018/1/27.
 */

public class GetSereenSizeUtil {

    /**
     * TODO:获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * TODO：获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
