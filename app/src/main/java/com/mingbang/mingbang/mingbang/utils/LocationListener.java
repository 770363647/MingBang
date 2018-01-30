package com.mingbang.mingbang.mingbang.utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.mingbang.mingbang.mingbang.ui.activity.attendance.AttendanceActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class LocationListener implements BDLocationListener {
    private final String TAG = "LocationListener";
    private AttendanceActivity aa;

    public LocationListener(AttendanceActivity aa) {
        this.aa = aa;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        float radius = location.getRadius();
        String coorType = location.getCoorType();
        int errorCode = location.getLocType();
        String addr = location.getAddrStr();
        String country = location.getCountry();
        String province = location.getProvince();
        String city = location.getCity();
        String district = location.getDistrict();
        String street = location.getStreet();

        aa.setLocation(latitude + "\n" + longitude + "\n" + country + "\n"
                + province + "\n" + city + "\n" + district + "\n" + street);


    }

}
