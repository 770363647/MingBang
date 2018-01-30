package com.mingbang.mingbang.mingbang.ui.activity.ordermanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class FactGetSendActivity extends BaseActivity implements View.OnClickListener {

    private TextView title;
    private TextView inputType;

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
        setContentView(R.layout.fact_get_send_layout);
        init();

    }

    private void init() {
        findById();
        initInterface();
    }

    private void findById() {
        title = findViewById(R.id.title);
        inputType = findViewById(R.id.inputType);
    }

    /**
     * TODO:初始化界面
     */
    private void initInterface() {
        Intent intent = getIntent();
        String titleStr = intent.getStringExtra("type");
        switch (titleStr) {
            case "get":
                title.setText(getResources().getString(R.string.recordRealGet));
                inputType.setText(getResources().getString(R.string.factGetNum));
                break;
            case "send":
                title.setText(getResources().getString(R.string.recordRealSend));
                inputType.setText(getResources().getString(R.string.factSendNum));
                break;
            default:
                break;
        }
    }


}