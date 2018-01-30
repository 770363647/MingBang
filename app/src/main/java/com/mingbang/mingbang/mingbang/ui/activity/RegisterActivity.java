package com.mingbang.mingbang.mingbang.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mingbang.mingbang.mingbang.R;

/**
 * @author: zhaojy
 * @data:On 2018/1/19.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "RegisterActivity";
    private ImageView bg;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:

                break;
            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            //应该在UI渲染完毕之后执行背景模糊操作
            super.dimOption(bg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);
        init();
    }

    private void init() {
        findById();
        setStatusBar();
    }

    private void findById() {
        bg = findViewById(R.id.bg);
    }

    /**
     * TODO:设置状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setStatusBar() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }



}
