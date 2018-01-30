package com.mingbang.mingbang.mingbang.ui.activity.administratorfunction;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.TaskSetItemAdapter;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/29.
 */

public class TaskSetActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

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
        setContentView(R.layout.task_set_layout);
        init();
    }

    private void init() {
        findById();
        setRecyclerView();
    }

    private void findById() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    /**
     * TODO:设置 recyclerview
     */
    private void setRecyclerView() {
        List data = new ArrayList();

        for (int i = 0; i < 16; i++) {
            data.add("");
        }

        TaskSetItemAdapter adapter = new TaskSetItemAdapter(data, this);
        adapter.setOnItemClickListener(new TaskSetItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }


}
