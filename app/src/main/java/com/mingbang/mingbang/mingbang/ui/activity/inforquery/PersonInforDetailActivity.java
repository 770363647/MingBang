package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/22.
 */

public class PersonInforDetailActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "PersonInforDetailActivity";

    private TextView name;

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
        setContentView(R.layout.person_infor_detail_layout);

        init();
    }

    private void init() {
        findById();
        setName();


    }

    private void findById() {
        name = findViewById(R.id.name);

    }

    private void setName() {
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
    }



}