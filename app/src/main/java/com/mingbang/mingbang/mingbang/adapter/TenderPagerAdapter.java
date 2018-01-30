package com.mingbang.mingbang.mingbang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class TenderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> pagerList;

    public TenderPagerAdapter(FragmentManager fm, List<Fragment> pagerList) {
        super(fm);
        this.pagerList = pagerList;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }
}
