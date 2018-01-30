package com.mingbang.mingbang.mingbang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mingbang.mingbang.mingbang.R;

/**
 * @author: zhaojy
 * @data:On 2018/1/19.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "LoginActivity";
    private ImageView bg;
    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.clientRegister:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.forgetPs:
                Toast.makeText(this, "请联系管理员", Toast.LENGTH_SHORT).show();
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

    private void init() {
        findById();
        this.setStatusBarTranparent();

    }

    private void findById() {
        bg = findViewById(R.id.bg);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
    }


}
