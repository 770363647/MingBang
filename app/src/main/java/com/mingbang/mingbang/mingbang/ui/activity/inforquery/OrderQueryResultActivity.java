package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/24.
 */

public class OrderQueryResultActivity extends BaseActivity implements View.OnClickListener {

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
        setContentView(R.layout.order_query_result_layout);

        init();
    }

    private void init() {
        findById();
    }

    private void findById() {

    }

}
