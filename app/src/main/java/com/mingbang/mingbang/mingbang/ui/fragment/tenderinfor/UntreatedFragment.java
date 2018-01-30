package com.mingbang.mingbang.mingbang.ui.fragment.tenderinfor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingbang.mingbang.mingbang.R;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class UntreatedFragment extends Fragment {

    private View view;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.untreated_tender_layout, null);
        init();

        return view;
    }

    private void init() {
        findById();

    }

    private void findById() {

    }
}
