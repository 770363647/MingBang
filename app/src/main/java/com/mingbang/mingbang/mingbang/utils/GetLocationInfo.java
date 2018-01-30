package com.mingbang.mingbang.mingbang.utils;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.mingbang.mingbang.mingbang.ui.activity.attendance.AttendanceActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class GetLocationInfo {

    public static void getLocation(AttendanceActivity aa) {
        LocationClient mLocationClient = new LocationClient(aa.getApplicationContext());
        LocationListener myListener = new LocationListener(aa);
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        // option.setScanSpan(10000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);

        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
}
