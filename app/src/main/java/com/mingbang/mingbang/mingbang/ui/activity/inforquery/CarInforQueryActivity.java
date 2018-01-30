package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.annotation.SuppressLint;
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
 * @data:On 2018/1/23.
 */

public class CarInforQueryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout areaSelect;
    private LinearLayout stateSelect;
    private LinearLayout belongSelect;

    private TextView area;
    private TextView state;
    private TextView belong;

    /**
     * 地区可选
     */
    private TextView allArea;
    private TextView chongqing;
    private TextView zunyi;
    private TextView tongren;

    /**
     * 状态可选
     */
    private TextView allState;
    private TextView leisure;
    private TextView busy;

    /**
     * 所属可选
     */
    private TextView allBelong;
    private TextView affiliated;
    private TextView firm;


    private ScrollView scrollView;

    private List<TextView> tvList;
    private List<LinearLayout> downList;
    private List<Boolean> showList;

    private List<TextView> areaList;
    private List<TextView> stateList;
    private List<TextView> belongList;

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
                hiddenAllSelectItem();
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
            case R.id.area:
                showSelectItem(0);
                break;
            case R.id.state:
                showSelectItem(1);
                break;
            case R.id.belong:
                showSelectItem(2);
                break;
            case R.id.areaHidden:
                hiddenAllSelectItem();
                break;
            case R.id.stateHidden:
                hiddenAllSelectItem();
                break;
            case R.id.belongHidden:
                hiddenAllSelectItem();
                break;
            case R.id.allArea:
                selectedItem(areaList, 0, area);
                break;
            case R.id.chongqing:
                selectedItem(areaList, 1, area);
                break;
            case R.id.zunyi:
                selectedItem(areaList, 2, area);
                break;
            case R.id.tongren:
                selectedItem(areaList, 3, area);
                break;
            case R.id.allState:
                selectedItem(stateList, 0, state);
                break;
            case R.id.leisure:
                selectedItem(stateList, 1, state);
                break;
            case R.id.busy:
                selectedItem(stateList, 2, state);
                break;
            case R.id.allBelong:
                selectedItem(belongList, 0, belong);
                break;
            case R.id.affiliated:
                selectedItem(belongList, 1, belong);
                break;
            case R.id.firm:
                selectedItem(belongList, 2, belong);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.car_infor_query_layout);

        init();
    }

    private void init() {
        findById();
        initSelectBar();
    }

    private void findById() {
        areaSelect = findViewById(R.id.areaSelect);
        stateSelect = findViewById(R.id.stateSelect);
        belongSelect = findViewById(R.id.belongSelect);

        area = findViewById(R.id.area);
        state = findViewById(R.id.state);
        belong = findViewById(R.id.belong);

        allArea = findViewById(R.id.allArea);
        chongqing = findViewById(R.id.chongqing);
        zunyi = findViewById(R.id.zunyi);
        tongren = findViewById(R.id.tongren);

        allState = findViewById(R.id.allState);
        leisure = findViewById(R.id.leisure);
        busy = findViewById(R.id.busy);

        allBelong = findViewById(R.id.allBelong);
        affiliated = findViewById(R.id.affiliated);
        firm = findViewById(R.id.firm);

        scrollView = findViewById(R.id.scrollView);

    }

    /**
     * TODO:初始化筛选栏
     */
    private void initSelectBar() {
        tvList = new ArrayList<>();
        tvList.add(area);
        tvList.add(state);
        tvList.add(belong);

        downList = new ArrayList<>();
        downList.add(areaSelect);
        downList.add(stateSelect);
        downList.add(belongSelect);

        showList = new ArrayList<>();
        showList.add(false);
        showList.add(false);
        showList.add(false);

        areaList = new ArrayList<>();
        areaList.add(allArea);
        areaList.add(chongqing);
        areaList.add(zunyi);
        areaList.add(tongren);

        stateList = new ArrayList<>();
        stateList.add(allState);
        stateList.add(leisure);
        stateList.add(busy);

        belongList = new ArrayList<>();
        belongList.add(allBelong);
        belongList.add(affiliated);
        belongList.add(firm);

    }

    /**
     * TODO:筛选栏项目被点击
     *
     * @param index
     */
    @SuppressLint({"ClickableViewAccessibility"})
    private void showSelectItem(int index) {
        hiddenAllSelectItem();
        selectPageIsShowing = true;
        //禁止滑动
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return true;
            }
        });

        tvList.get(index).setTextColor(getResources().getColor(R.color.theme));
        Drawable drawable = getResources().getDrawable(R.mipmap.down_list);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        tvList.get(index).setCompoundDrawables(null, null, drawable, null);

        downList.get(index).setVisibility(View.VISIBLE);

        showList.set(index, true);

    }

    /**
     * TODO:隐藏筛选栏
     */
    @SuppressLint("ResourceAsColor")
    private void hiddenAllSelectItem() {
        selectPageIsShowing = false;
        //允许滑动
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });

        for (int index = 0; index < showList.size(); index++) {
            if (showList.get(index)) {
                tvList.get(index).setTextColor(getResources().getColor(R.color.black));
                Drawable drawable = getResources().getDrawable(R.mipmap.dark_down_list);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                tvList.get(index).setCompoundDrawables(null, null, drawable, null);

                downList.get(index).setVisibility(View.GONE);

                showList.set(index, false);
            }
        }

    }


    /**
     * TODO:筛选选择项被选择
     *
     * @param list     TextView  集合
     * @param index    索引下标
     * @param targetTv
     */
    private void selectedItem(List<TextView> list, int index, TextView targetTv) {
        //全部取消选择
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setTextColor(getResources().getColor(R.color.black));
        }

        list.get(index).setTextColor(getResources().getColor(R.color.theme));
        String str = list.get(index).getText().toString();
        targetTv.setText(str);

        hiddenAllSelectItem();
        selectPageIsShowing = false;
    }

}
