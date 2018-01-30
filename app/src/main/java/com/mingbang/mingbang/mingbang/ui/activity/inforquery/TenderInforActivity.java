package com.mingbang.mingbang.mingbang.ui.activity.inforquery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.TenderPagerAdapter;
import com.mingbang.mingbang.mingbang.ui.activity.BaseActivity;
import com.mingbang.mingbang.mingbang.ui.fragment.tenderinfor.ProcessedFragment;
import com.mingbang.mingbang.mingbang.ui.fragment.tenderinfor.UntreatedFragment;
import com.mingbang.mingbang.mingbang.utils.GetSereenSizeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/26.
 */

public class TenderInforActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "TenderInforActivity";

    private TextView untreated;
    private TextView processed;
    private View indicator;

    private Fragment untreatedFragment;
    private Fragment processedFragment;

    private ViewPager viewPager;
    private List<Fragment> pagerList;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.untreated:
                viewPager.setCurrentItem(0);
                break;
            case R.id.processed:
                viewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tender_infor_layout);
        init();

    }

    private void init() {
        findById();
        setViewPager();
        initIndicator();
    }

    private void findById() {
        untreated = findViewById(R.id.untreated);
        processed = findViewById(R.id.processed);
        indicator = findViewById(R.id.indicataor);
        viewPager = findViewById(R.id.viewPager);
    }

    /**
     * TODO:初始化指示条
     */
    private void initIndicator() {
        int w = GetSereenSizeUtil.getScreenWidth(this) / 2;
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) indicator.getLayoutParams();
        ll.width = w / 2;
        ll.setMargins(w / 4, 0, 0, 0);
        indicator.setLayoutParams(ll);
    }

    /**
     * TODO:初始化ViewPager页面
     */
    private void setViewPager() {
        pagerList = new ArrayList<>();
        untreatedFragment = new UntreatedFragment();
        processedFragment = new ProcessedFragment();
        pagerList.add(untreatedFragment);
        pagerList.add(processedFragment);

        FragmentManager fm = this.getSupportFragmentManager();
        viewPager.setAdapter(new TenderPagerAdapter(fm, pagerList));
        viewPager.setCurrentItem(0);
        /*滑动监听*/
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                selectedOption(pos);
            }

            @Override
            public void onPageScrolled(int pos, float offsetPercentage, int offsetPixel) {
                setIndicatorPos(offsetPercentage);
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    /**
     * TODO:设置指示器位置
     *
     * @param percentage 位置百分比
     */
    private void setIndicatorPos(float percentage) {
        if (percentage == 0.0) {
            return;
        }
        int w = GetSereenSizeUtil.getScreenWidth(this) / 2;
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) indicator.getLayoutParams();
        ll.setMargins((int) (w * percentage) + w / 4, 0, 0, 0);
        indicator.setLayoutParams(ll);
    }

    /**
     * TODO:选中处理
     *
     * @param pos 索引下标
     */
    private void selectedOption(int pos) {
        switch (pos) {
            case 0:
                selected(untreated);
                unSelected(processed);
                break;
            case 1:
                selected(processed);
                unSelected(untreated);
                break;
            default:
                break;
        }
    }

    /**
     * TODO:被选中
     *
     * @param tv
     */
    private void selected(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.theme));
    }

    /**
     * TODO:未被选中
     *
     * @param tv
     */
    private void unSelected(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.black));
    }


}
