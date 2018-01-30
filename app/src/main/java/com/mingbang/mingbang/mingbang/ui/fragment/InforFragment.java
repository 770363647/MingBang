package com.mingbang.mingbang.mingbang.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.InforItemAdapter;
import com.mingbang.mingbang.mingbang.bean.InforItemBean;
import com.mingbang.mingbang.mingbang.ui.activity.AccidentActivity;
import com.mingbang.mingbang.mingbang.ui.activity.ApproveActivity;
import com.mingbang.mingbang.mingbang.ui.activity.FinancalStateActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/18.
 */
public class InforFragment extends Fragment {
    private final String TAG = "InforFragment";

    private Context context;
    private View view;
    private RecyclerView recyclerView;
    private EditText search;
    private TextView englishCompany;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.infor_layout, null);
        init();
        return view;
    }

    private void init() {
        context = this.getActivity();
        findById();
        setElasticHeader();
        setRecyclerView();
        setListener();
        setCompanyNameFont();

    }

    private void findById() {
        recyclerView = view.findViewById(R.id.recyclerView);
        search = view.findViewById(R.id.search);
        englishCompany = view.findViewById(R.id.englishCompany);
    }

    /**
     * TODO:设置弹性头部
     */
    private void setElasticHeader() {
        final RefreshLayout refreshLayout = view.findViewById(R.id.elastic);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(0);
            }
        });
        //是否启用越界回弹
        refreshLayout.setEnableOverScrollBounce(true);
    }

    /**
     * TODO:设置 recyclerview
     */
    private void setRecyclerView() {
        final List<InforItemBean> data = new ArrayList<>();
        final String[] title = {"事故处理", "审批通知", "财务报表", "张三丰",
                "事故处理", "审批通知", "财务报表", "张三丰",
                "事故处理", "审批通知", "财务报表", "张三丰"};
        final String[] time = {"9:30", "8:00", "8:30", "9:00",
                "9:30", "8:00", "8:30", "9:00",
                "9:30", "8:00", "8:30", "9:00"};
        final String[] content = {"出事故啦!!!", "您有订单需要审批", "昨日财务日报，快来看看吧!", "hello",
                "出事故啦!!!", "您有订单需要审批", "昨日财务日报，快来看看吧!", "hello",
                "出事故啦!!!", "您有订单需要审批", "昨日财务日报，快来看看吧!", "hello"};

        final Integer[] logo = {R.mipmap.accident, R.mipmap.approve, R.mipmap.finance, R.mipmap.friend,
                R.mipmap.accident, R.mipmap.approve, R.mipmap.finance, R.mipmap.friend,
                R.mipmap.accident, R.mipmap.approve, R.mipmap.finance, R.mipmap.friend};

        for (int i = 0; i < title.length; i++) {
            InforItemBean it = new InforItemBean();
            it.setTitle(title[i]);
            it.setTime(time[i]);
            it.setContent(content[i]);
            it.setLogo(logo[i]);
            data.add(it);
        }

        InforItemAdapter adapter = new InforItemAdapter(data, this.getActivity());
        adapter.setOnItemClickListener(new InforItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String title = data.get(position).getTitle();
                switch (title) {
                    case "事故处理":
                        Intent intent = new Intent(context, AccidentActivity.class);
                        startActivity(intent);
                        break;
                    case "审批通知":
                        Intent intent2 = new Intent(context, ApproveActivity.class);
                        startActivity(intent2);
                        break;
                    case "财务报表":
                        Intent intent3 = new Intent(context, FinancalStateActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    /**
     * TODO:设置监听
     */
    private void setListener() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                }
                return false;
            }
        });
    }

    /**
     * TODO:设置公司名字体
     */
    private void setCompanyNameFont() {
        Typeface typeface = Typeface.createFromAsset(this.getActivity().getAssets(),
                "fonts/LuciaBT.ttf");
        englishCompany.setTypeface(typeface);
    }


}
