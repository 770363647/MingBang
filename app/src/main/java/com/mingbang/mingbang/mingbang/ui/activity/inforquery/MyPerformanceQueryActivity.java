package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;
import com.mingbang.mingbang.mingbang.view.DialView;
import com.mingbang.mingbang.mingbang.view.PickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/28.
 */

public class MyPerformanceQueryActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "MyPerformanceQueryActivity";

    private LinearLayout monthCalendar;
    private LinearLayout statusBar;

    private PickerView yearList;
    private PickerView monthList;

    private String selectedYear;
    private String selectedMonth;

    private List<String> yearData;
    private List<String> monthData;

    private TextView date;

    /**
     * 当前年月日
     */
    private int curYear;
    private int curMonth;

    /**
     * 日历选择控件是否显示
     */
    private boolean calendarIsShowing = false;

    private DialView dialView;
    private int value;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                update();
            }
            handler.sendEmptyMessageDelayed(1, 1);
        }
    };

    private void update() {
        if (value > 1200) {
            value = 1200;
            handler.removeMessages(1);
        }
        dialView.setmCreditScore(value);
        dialView.requestLayout();
        dialView.invalidate();
        value += 4;
    }

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
            if (calendarIsShowing) {
                hiddenCalendar();
                return false;
            } else {
                this.finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private int curScore = 600;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.calendar:
                showCalendar();
                break;
            case R.id.cancel:
                hiddenCalendar();
                break;
            case R.id.asure:
                //容错处理
                if (selectedYear.equals("") || selectedMonth.equals("")) {
                    Toast.makeText(this, "非法选择", Toast.LENGTH_SHORT).show();
                    return;
                }
                hiddenCalendar();
                String str = "";
                if (Integer.valueOf(selectedYear) == curYear &&
                        Integer.valueOf(selectedMonth) == curMonth) {
                    str = "本月";
                } else {
                    str = selectedYear + "/" + selectedMonth;
                }
                date.setText(str);
                break;

            case R.id.start:
                value = 0;
                handler.sendEmptyMessage(1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_performance_query_layout);

        init();
    }

    private void init() {
        findById();
        setStatusBar();
    }

    private void findById() {
        monthCalendar = findViewById(R.id.monthCalendar);
        statusBar = findViewById(R.id.statusBar);
        yearList = findViewById(R.id.year);
        monthList = findViewById(R.id.month);
        date = findViewById(R.id.date);

        dialView = findViewById(R.id.dialView);

    }

    /**
     * TODO:设置状态栏
     */
    private void setStatusBar() {
        this.setStatusBarTranparent();

        double statusBarHeight = this.getStatusBarHeight(this);
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams)
                statusBar.getLayoutParams();
        ll.height = (int) statusBarHeight;
        statusBar.setLayoutParams(ll);
    }

    /**
     * TODO:显示日历选择
     */
    private void showCalendar() {
        initCalendar();
        calendarIsShowing = true;
        monthCalendar.setVisibility(View.VISIBLE);
    }

    /**
     * TODO:隐藏日历选择
     */
    private void hiddenCalendar() {
        calendarIsShowing = false;
        monthCalendar.setVisibility(View.GONE);
    }

    /**
     * TODO:初始化日历
     */
    public void initCalendar() {
        //获取当前年月日
        Calendar c = Calendar.getInstance();
        curYear = c.get(Calendar.YEAR);
        curMonth = c.get(Calendar.MONTH) + 1;

        selectedYear = String.valueOf(curYear);
        selectedMonth = String.valueOf(curMonth);

        yearData = new ArrayList<>();
        monthData = new ArrayList<>();

        for (int i = 1949; i <= curYear; i++) {
            yearData.add(String.valueOf(i));
        }
        for (int i = 1; i <= curMonth; i++) {
            monthData.add(String.valueOf(i));
        }

        for (int i = 0; i < 2; i++) {
            yearData.add("");
            monthData.add("");
        }

        yearList.setData(yearData);
        yearList.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                selectedYear = text;
                if (!String.valueOf(curYear).equals(selectedYear)) {
                    selectedMonth = "12";
                } else {
                    selectedMonth = String.valueOf(curMonth);
                }

                yearSelected(text);
            }
        });

        monthList.setData(monthData);
        monthList.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                selectedMonth = text;
            }
        });

    }

    /**
     * TODO:年份被选择处理
     *
     * @param year
     */
    private void yearSelected(String year) {
        if (year.equals("")) {
            monthData.clear();
            for (int i = 0; i < 3; i++) {
                monthData.add("");
            }
            monthList.setData(monthData);
            return;
        }
        int yearSelect = Integer.valueOf(year);
        monthData.clear();
        if (yearSelect == curYear) {
            for (int i = 1; i <= curMonth; i++) {
                monthData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                monthData.add("");
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                monthData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                monthData.add("");
            }

        }
        monthList.setData(monthData);

    }

}
