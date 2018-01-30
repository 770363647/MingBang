package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/28.
 */

public class SalemanTaskQueryActivity extends BaseActivity implements View.OnClickListener {


    private List<TextView> sortList;

    private TextView sortWay;
    private LinearLayout sortWaySelect;

    private TextView noSort;
    private TextView scheduleTopToBottom;
    private TextView scheduleBottomToTop;
    private TextView finishTopToBottom;
    private TextView finishBottomToTop;
    private TextView moneyTopToBottom;
    private TextView moneyBottomToTop;

    private ScrollView scrollView;

    /**
     * 选择页面是否显示
     */
    private boolean selectPageIsShowing = false;

    /**
     * TODO：返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (selectPageIsShowing) {
                hiddenSortWaySelectList();
                return false;
            } else {
                this.finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.sortWay:
                showSortWaySelectList();
                break;
            case R.id.noSort:
                selectedSortWayItem(noSort);
                break;
            case R.id.scheduleTopToBottom:
                selectedSortWayItem(scheduleTopToBottom);
                break;
            case R.id.scheduleBottomToTop:
                selectedSortWayItem(scheduleBottomToTop);
                break;
            case R.id.finishTopToBottom:
                selectedSortWayItem(finishTopToBottom);
                break;
            case R.id.finishBottomToTop:
                selectedSortWayItem(finishBottomToTop);
                break;
            case R.id.moneyTopToBottom:
                selectedSortWayItem(moneyTopToBottom);
                break;
            case R.id.moneyBottomToTop:
                selectedSortWayItem(moneyBottomToTop);
                break;
            case R.id.hidden:
                hiddenSortWaySelectList();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.saleman_task_query_layout);

        init();
    }

    private void init() {
        findById();
        initSortList();
    }

    private void findById() {
        sortWay = findViewById(R.id.sortWay);
        sortWaySelect = findViewById(R.id.sortWaySelect);

        noSort = findViewById(R.id.noSort);
        scheduleTopToBottom = findViewById(R.id.scheduleTopToBottom);
        scheduleBottomToTop = findViewById(R.id.scheduleBottomToTop);
        finishTopToBottom = findViewById(R.id.finishTopToBottom);
        finishBottomToTop = findViewById(R.id.finishBottomToTop);
        moneyTopToBottom = findViewById(R.id.moneyTopToBottom);
        moneyBottomToTop = findViewById(R.id.moneyBottomToTop);

        scrollView = findViewById(R.id.scrollView);
    }

    /**
     * TODO:初始化排序方式集合
     */
    private void initSortList() {
        sortList = new ArrayList<>();
        sortList.add(noSort);
        sortList.add(scheduleTopToBottom);
        sortList.add(scheduleBottomToTop);
        sortList.add(finishTopToBottom);
        sortList.add(finishBottomToTop);
        sortList.add(moneyTopToBottom);
        sortList.add(moneyBottomToTop);

    }

    /**
     * TODO:选中的排序方式
     */
    private void selectedSortWayItem(TextView tv) {
        //将所有的item置为未选中
        for (int i = 0; i < sortList.size(); i++) {
            sortList.get(i).setTextColor(getResources().getColor(R.color.black));
        }
        tv.setTextColor(getResources().getColor(R.color.theme));
        String infor = tv.getText().toString();
        sortWay.setText(infor);
        hiddenSortWaySelectList();
    }

    /**
     * TODO:显示排序方式下拉列表
     */
    private void showSortWaySelectList() {
        selectPageIsShowing = true;
        sortWaySelect.setVisibility(View.VISIBLE);

        //禁止滑动
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        sortWay.setTextColor(getResources().getColor(R.color.theme));
        Drawable drawable = getResources().getDrawable(R.mipmap.down_list);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        sortWay.setCompoundDrawables(null, null, drawable, null);

    }

    /**
     * TODO:隐藏排序方式选择列表
     */
    private void hiddenSortWaySelectList() {
        selectPageIsShowing = false;
        sortWaySelect.setVisibility(View.GONE);
        //禁止滑动
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        sortWay.setTextColor(getResources().getColor(R.color.black));
        Drawable drawable = getResources().getDrawable(R.mipmap.dark_down_list);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());

        sortWay.setCompoundDrawables(null, null, drawable, null);
    }

}
