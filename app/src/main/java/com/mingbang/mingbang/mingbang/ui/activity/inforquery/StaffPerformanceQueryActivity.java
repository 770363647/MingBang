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
import android.widget.Toast;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;
import com.mingbang.mingbang.mingbang.view.PickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/27.
 */

public class StaffPerformanceQueryActivity extends BaseActivity implements View.OnClickListener {

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


    private List<TextView> sortList;

    private TextView sortWay;
    private LinearLayout sortWaySelect;
    private TextView noSort;
    private TextView fromTopToBottom;
    private TextView fromBottomToTop;

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
            if (calendarIsShowing) {
                hiddenCalendar();
                return false;
            } else if (selectPageIsShowing) {
                hiddenSortWaySelectList();
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
            case R.id.sortWay:
                showSortWaySelectList();
                break;
            case R.id.hidden:
                hiddenSortWaySelectList();
                break;
            case R.id.noSort:
                selectedSortWayItem(noSort);
                break;
            case R.id.fromTopToBottom:
                selectedSortWayItem(fromTopToBottom);
                break;
            case R.id.fromBottomToTop:
                selectedSortWayItem(fromBottomToTop);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.staff_performance_query_layout);

        init();
    }

    private void init() {
        findById();
        setStatusBar();
        initSortList();
    }

    private void findById() {
        monthCalendar = findViewById(R.id.monthCalendar);
        statusBar = findViewById(R.id.statusBar);
        yearList = findViewById(R.id.year);
        monthList = findViewById(R.id.month);
        date = findViewById(R.id.date);

        sortWay = findViewById(R.id.sortWay);
        sortWaySelect = findViewById(R.id.sortWaySelect);
        scrollView = findViewById(R.id.scrollView);
        noSort = findViewById(R.id.noSort);
        fromBottomToTop = findViewById(R.id.fromBottomToTop);
        fromTopToBottom = findViewById(R.id.fromTopToBottom);
    }

    /**
     * TODO:初始化排序方式集合
     */
    private void initSortList() {
        sortList = new ArrayList<>();
        sortList.add(noSort);
        sortList.add(fromBottomToTop);
        sortList.add(fromTopToBottom);
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
        selectPageIsShowing = false;
        hiddenSortWaySelectList();
    }


}
