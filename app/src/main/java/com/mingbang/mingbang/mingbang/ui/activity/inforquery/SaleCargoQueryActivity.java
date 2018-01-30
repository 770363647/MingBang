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
 * @data:On 2018/1/28.
 */

public class SaleCargoQueryActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout brandSelect;
    private LinearLayout specificationSelect;
    private LinearLayout packSelect;

    private TextView brand;
    private TextView specification;
    private TextView pack;

    private ScrollView scrollView;

    private List<TextView> tvList;
    private List<LinearLayout> downList;
    private List<Boolean> showList;

    private List<TextView> brandList;
    private List<TextView> specificationList;
    private List<TextView> packList;


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
            case R.id.brand:
                showSelectItem(0);
                break;
            case R.id.specification:
                showSelectItem(1);
                break;
            case R.id.pack:
                showSelectItem(2);
                break;
            case R.id.brandHidden:
                hiddenAllSelectItem();
                break;
            case R.id.specificationHidden:
                hiddenAllSelectItem();
                break;
            case R.id.packHidden:
                hiddenAllSelectItem();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sale_cargo_layout);
        init();
    }

    private void init() {
        findById();
        initSelectBar();
    }

    private void findById() {
        brandSelect = findViewById(R.id.brandSelect);
        specificationSelect = findViewById(R.id.specificationSelect);
        packSelect = findViewById(R.id.packSelect);

        brand = findViewById(R.id.brand);
        specification = findViewById(R.id.specification);
        pack = findViewById(R.id.pack);

        scrollView = findViewById(R.id.scrollView);
    }

    /**
     * TODO:初始化筛选栏
     */
    private void initSelectBar() {
        tvList = new ArrayList<>();
        tvList.add(brand);
        tvList.add(specification);
        tvList.add(pack);

        downList = new ArrayList<>();
        downList.add(brandSelect);
        downList.add(specificationSelect);
        downList.add(packSelect);

        showList = new ArrayList<>();
        showList.add(false);
        showList.add(false);
        showList.add(false);

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
    }

}
