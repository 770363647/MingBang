package com.mingbang.mingbang.mingbang.ui.activity.administratorfunction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class PermissionSetting extends BaseActivity implements View.OnClickListener {

    private LinearLayout finance;

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
        setContentView(R.layout.permission_setting_layout);
        init();

    }

    private void init() {
        findById();
        setListener();
    }

    private void findById() {
        finance = findViewById(R.id.finance);

    }

    private void setListener() {
        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PermissionSetting.this, FinanceActivity.class);
                startActivity(intent);
            }
        });
    }

}