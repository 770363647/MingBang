package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.StaffAttendItemAdapter;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;
import com.mingbang.mingbang.mingbang.utils.CalendarUtil;
import com.mingbang.mingbang.mingbang.utils.GetDayOfMounthUtil;
import com.mingbang.mingbang.mingbang.view.PickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class StaffAttendActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout dayCalendar;
    private LinearLayout statusBar;

    private PickerView yearList;
    private PickerView monthList;
    private PickerView dayList;

    private String selectedYear;
    private String selectedMonth;
    private String selectedDay;

    private List<String> yearData;
    private List<String> monthData;
    private List<String> dayData;

    private TextView date;

    /**
     * 当前年月日
     */
    private int curYear;
    private int curMonth;
    private int curDay;

    /**
     * 日历选择覆盖层是否显示
     */
    private boolean calendarIsShowing = false;

    private RecyclerView recyclerView;

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
                calendarAsure();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.staff_attend_layout);

        init();
    }

    private void init() {
        findById();
        setStatusBar();
        setRecyclerView();
    }

    private void findById() {
        dayCalendar = findViewById(R.id.dayCalendar);
        statusBar = findViewById(R.id.statusBar);

        date = findViewById(R.id.date);

        yearList = findViewById(R.id.year);
        monthList = findViewById(R.id.month);
        dayList = findViewById(R.id.day);
        recyclerView = findViewById(R.id.recyclerView);
    }

    /**
     * TODO:设置 recyclerview
     */
    private void setRecyclerView() {

        List data = new ArrayList();
        for (int i = 0; i < 18; i++) {
            data.add("");
        }

        StaffAttendItemAdapter adapter = new StaffAttendItemAdapter(data, this);
        adapter.setOnItemClickListener(new StaffAttendItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    /**
     * TODO:显示日历选择
     */
    private void showCalendar() {
        initCalendar();
        calendarIsShowing = true;
        dayCalendar.setVisibility(View.VISIBLE);
    }

    /**
     * TODO:隐藏日历选择
     */
    private void hiddenCalendar() {
        calendarIsShowing = false;
        dayCalendar.setVisibility(View.GONE);
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
     * TODO:初始化日历
     */
    public void initCalendar() {
        //获取当前年月日
        curYear = CalendarUtil.getYear();
        curMonth = CalendarUtil.getMonth();
        curDay = CalendarUtil.getDay();

        selectedYear = String.valueOf(curYear);
        selectedMonth = String.valueOf(curMonth);
        selectedDay = String.valueOf(curDay);

        yearData = new ArrayList<>();
        monthData = new ArrayList<>();
        dayData = new ArrayList<>();

        for (int i = 1949; i <= curYear; i++) {
            yearData.add(String.valueOf(i));
        }
        for (int i = 1; i <= curMonth; i++) {
            monthData.add(String.valueOf(i));
        }
        for (int i = 1; i <= curDay; i++) {
            dayData.add(String.valueOf(i));
        }

        for (int i = 0; i < 2; i++) {
            yearData.add("");
            monthData.add("");
            dayData.add("");
        }

        yearList.setData(yearData);
        yearList.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                selectedYear = text;
                if (selectedYear.equals(String.valueOf(curYear))) {
                    selectedMonth = String.valueOf(curMonth);
                    selectedDay = String.valueOf(curDay);
                } else {
                    selectedMonth = String.valueOf("12");
                    selectedDay = String.valueOf(GetDayOfMounthUtil.
                            getDayOfMonth(curYear, curMonth));
                }
                yearSelected(text);
            }
        });

        monthList.setData(monthData);
        monthList.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                selectedMonth = text;
                if (selectedYear.equals(String.valueOf(curYear)) &&
                        selectedMonth.equals(String.valueOf(curMonth))) {
                    selectedDay = String.valueOf(curDay);
                } else {
                    if (selectedMonth.equals("")) {
                        selectedDay = "";
                        return;
                    }
                    selectedDay = String.valueOf(GetDayOfMounthUtil.
                            getDayOfMonth(Integer.valueOf(selectedYear),
                                    Integer.valueOf(selectedMonth)));
                }
                monthSelected(text);
            }
        });

        dayList.setData(dayData);
        dayList.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                selectedDay = text;
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
            dayData.clear();
            for (int i = 0; i < 3; i++) {
                monthData.add("");
                dayData.add("");
            }
            monthList.setData(monthData);
            dayList.setData(dayData);
            return;
        }
        int yearSelect = Integer.valueOf(year);
        monthData.clear();
        dayData.clear();
        if (yearSelect == curYear) {
            for (int i = 1; i <= curMonth; i++) {
                monthData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                monthData.add("");
            }
            for (int i = 1; i <= curDay; i++) {
                dayData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                dayData.add("");
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                monthData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                monthData.add("");
            }
            for (int i = 1; i <= GetDayOfMounthUtil.getDayOfMonth(yearSelect, 12);
                 i++) {
                dayData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                dayData.add("");
            }
        }
        monthList.setData(monthData);
        dayList.setData(dayData);
    }

    /**
     * TODO:月份被选择处理
     *
     * @param month
     */
    private void monthSelected(String month) {
        if (month.equals("")) {
            dayData.clear();
            for (int i = 0; i < 3; i++) {
                dayData.add("");
            }
            dayList.setData(dayData);
            return;
        }
        int yearSelect = Integer.valueOf(month);
        int monthSelect = Integer.valueOf(month);
        dayData.clear();
        if (monthSelect == curMonth && yearSelect == curYear) {
            for (int i = 1; i <= curDay; i++) {
                dayData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                dayData.add("");
            }
        } else {
            for (int i = 1; i <= GetDayOfMounthUtil.getDayOfMonth(yearSelect, monthSelect);
                 i++) {
                dayData.add(String.valueOf(i));
            }
            for (int i = 0; i < 2; i++) {
                dayData.add("");
            }
        }
        dayList.setData(dayData);
    }

    /**
     * TODO:日历选择确定按钮点击处理
     */
    private void calendarAsure() {
        if (selectedYear.equals("") || selectedMonth.equals("") ||
                selectedDay.equals("")) {
            Toast.makeText(this, "非法选择", Toast.LENGTH_SHORT).show();
            return;
        }
        hiddenCalendar();
        String str = "";
        if (selectedYear.equals(String.valueOf(curYear)) &&
                selectedMonth.equals(String.valueOf(curMonth)) &&
                selectedDay.equals(String.valueOf(curDay))) {
            str = "今日";
        } else {
            str = selectedYear + "." + selectedMonth + "." + selectedDay;
        }
        date.setText(str);
    }

}
