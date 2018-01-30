package com.mingbang.mingbang.mingbang.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.AccidentItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/20.
 */

public class AccidentActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List data = null;
    private AccidentItemAdapter adapter = null;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.accident_layout);
        init();
    }

    private void init() {
        findById();
        setRecyclerView();
        setSwipeRefreshLayout();
    }

    private void findById() {
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    /**
     * TODO:设置 recyclerview
     */
    private void setRecyclerView() {
        data = new ArrayList();

        for (int i = 0; i < 6; i++) {
            data.add(i);
        }

        adapter = new AccidentItemAdapter(data, this);
        adapter.setOnItemClickListener(new AccidentItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);

    }

    /**
     * TODO:设置SwipeRefreshLayout
     */
    private void setSwipeRefreshLayout() {
        //设置颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 6; i++) {
                            data.add(0,i);
                        }
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(5);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }

        });
    }

}
