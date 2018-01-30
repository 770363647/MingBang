package com.mingbang.mingbang.mingbang.ui.activity.carmanage;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/25.
 */


public class AccidentDisposeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.accident_dispose_layout);
    }

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
}