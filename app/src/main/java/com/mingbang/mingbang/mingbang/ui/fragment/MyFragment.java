package com.mingbang.mingbang.mingbang.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingbang.mingbang.mingbang.R;

/**
 * @author: zhaojy
 * @data:On 2018/1/23.
 */

public class MyFragment extends Fragment {

    private Context context;
    private View view;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_layout, null);
        init();
        return view;
    }

    private void init() {
        context = this.getActivity();
        findById();


    }

    private void findById() {

    }
}
