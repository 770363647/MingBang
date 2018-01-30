package com.mingbang.mingbang.mingbang.ui.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.adapter.ContactsItemAdapter;
import com.mingbang.mingbang.mingbang.bean.ContactsItemBean;
import com.mingbang.mingbang.mingbang.view.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhaojy
 * @data:On 2018/1/18.
 */

public class ContactsFragment extends Fragment {
    private final String TAG = "ContactsFragment";

    private View view;

    private RelativeLayout custom;
    private RelativeLayout innearContacts;
    private RelativeLayout custom_1;
    private RelativeLayout innearContacts_1;

    private RecyclerView customList;
    private RecyclerView innearContactsList;

    private boolean customListIsShow = false;
    private boolean innearConListIsShow = false;

    private ContactsItemAdapter customAdapter;
    private ContactsItemAdapter innearAdapter;

    private ImageView arrow1;
    private ImageView arrow2;
    private ImageView arrow1_1;
    private ImageView arrow2_1;

    private ScrollView scrollView;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contacts_layout, null);

        init();
        return view;
    }

    private void init() {
        findById();
        setListener();
        setCustomList();
        setInnearContactsList();

    }

    private void findById() {
        custom = view.findViewById(R.id.custom);
        innearContacts = view.findViewById(R.id.innearContacts);
        custom_1 = view.findViewById(R.id.custom_1);
        innearContacts_1 = view.findViewById(R.id.innearContacts_1);
        customList = view.findViewById(R.id.customList);
        innearContactsList = view.findViewById(R.id.innearContactsList);

        arrow1 = view.findViewById(R.id.arrow1);
        arrow2 = view.findViewById(R.id.arrow2);
        arrow1_1 = view.findViewById(R.id.arrow1_1);
        arrow2_1 = view.findViewById(R.id.arrow2_1);

        scrollView = view.findViewById(R.id.scrollView);
    }

    /**
     * TODO:设置监听事件
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void setListener() {
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customListIsShow) {
                    customList.setVisibility(View.GONE);
                    customListIsShow = false;

                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow1, "rotation",
                            0f, 0f);
                    anim.setDuration(100);
                    anim.start();


                } else {
                    showCustomList();
                }
            }
        });

        custom_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customListIsShow) {
                    customList.setVisibility(View.GONE);
                    custom_1.setVisibility(View.GONE);
                    customListIsShow = false;

                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow1, "rotation",
                            0f, 0f);
                    anim.setDuration(100);
                    anim.start();

                    ObjectAnimator anim1 = ObjectAnimator.ofFloat(arrow1_1, "rotation",
                            0f, 0f);
                    anim1.setDuration(100);
                    anim1.start();

                } else {
                    showCustomList();
                }
            }
        });

        innearContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (innearConListIsShow) {
                    innearContactsList.setVisibility(View.GONE);
                    innearConListIsShow = false;

                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow2, "rotation",
                            0f, 0f);
                    anim.setDuration(100);
                    anim.start();

                } else {
                    showInnearContactsList();
                    //滑动到指定的位置
                    //客户分组栏高度
                    int ch = getViewHeight(custom);
                    scrollView.smoothScrollTo(0, ch);
                }
            }
        });

        innearContacts_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (innearConListIsShow) {
                    innearContactsList.setVisibility(View.GONE);
                    innearContacts_1.setVisibility(View.GONE);
                    innearConListIsShow = false;

                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow2, "rotation",
                            0f, 0f);
                    anim.setDuration(100);
                    anim.start();

                    ObjectAnimator anim1 = ObjectAnimator.ofFloat(arrow2_1, "rotation",
                            0f, 0f);
                    anim1.setDuration(100);
                    anim1.start();
                } else {
                    showInnearContactsList();
                }
            }
        });

        scrollView.setOnScrollChangeListener(new ObservableScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View view, int x, int y, int oldx, int oldy) {
                //客户分组栏高度
                int ch = getViewHeight(custom);

                if (y > 0 && customListIsShow) {
                    custom_1.setVisibility(View.VISIBLE);
                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow1_1, "rotation",
                            0f, 90f);
                    anim.setDuration(0);
                    anim.start();
                } else if (y > ch && innearConListIsShow) {
                    innearContacts_1.setVisibility(View.VISIBLE);
                    ObjectAnimator anim = ObjectAnimator.ofFloat(arrow2_1, "rotation",
                            0f, 90f);
                    anim.setDuration(0);
                    anim.start();
                } else if (y == 0) {
                    custom_1.setVisibility(View.GONE);
                } else if (y < ch) {
                    innearContacts_1.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * TODO:设置客户下拉列表
     */
    private void setCustomList() {
        List<ContactsItemBean> data = new ArrayList();
        String[] name = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯",
                "陈", "褚", "卫", "蒋", "沈", "杨", "韩"};

        for (int i = 0; i < name.length; i++) {
            ContactsItemBean cib = new ContactsItemBean();
            cib.setName(name[i]);
            data.add(cib);
        }

        customAdapter = new ContactsItemAdapter(data, this.getActivity());

        customAdapter.setOnItemClickListener(new ContactsItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false) {

            @Override
            public boolean canScrollVertically() {

                return false;
            }
        };
        customList.setLayoutManager(linearLayoutManager);
        customList.setAdapter(customAdapter);
    }

    /**
     * TODO:设置内部联系人下拉列表
     */
    private void setInnearContactsList() {
        List<ContactsItemBean> data = new ArrayList();
        String[] name = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯",
                "陈", "褚", "卫", "蒋", "沈", "杨", "韩"};

        for (int i = 0; i < name.length; i++) {
            ContactsItemBean cib = new ContactsItemBean();
            cib.setName(name[i]);
            data.add(cib);
        }

        innearAdapter = new ContactsItemAdapter(data, this.getActivity());
        innearAdapter.setOnItemClickListener(new ContactsItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        innearContactsList.setLayoutManager(linearLayoutManager);
        innearContactsList.setAdapter(innearAdapter);
    }

    /**
     * TODO:显示客户下拉列表
     */
    private void showCustomList() {
        customList.setVisibility(View.VISIBLE);
        customListIsShow = true;

        innearContactsList.setVisibility(View.GONE);
        innearContacts_1.setVisibility(View.GONE);
        innearConListIsShow = false;

        ObjectAnimator anim = ObjectAnimator.ofFloat(arrow1, "rotation",
                0f, 90f);
        anim.setDuration(100);
        anim.start();

        ObjectAnimator anim2 = ObjectAnimator.ofFloat(arrow2, "rotation",
                0f, 0f);
        anim2.setDuration(100);
        anim2.start();
    }

    /**
     * TODO:显示内部联系人下拉列表
     */
    private void showInnearContactsList() {
        innearContactsList.setVisibility(View.VISIBLE);
        innearConListIsShow = true;

        customList.setVisibility(View.GONE);
        custom_1.setVisibility(View.GONE);
        customListIsShow = false;

        ObjectAnimator anim = ObjectAnimator.ofFloat(arrow2, "rotation",
                0f, 90f);
        anim.setDuration(100);
        anim.start();

        ObjectAnimator anim2 = ObjectAnimator.ofFloat(arrow1, "rotation",
                0f, 0f);
        anim2.setDuration(100);
        anim2.start();
    }

    /**
     * TODO:获取view的高度
     */
    private int getViewHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        int h = view.getMeasuredHeight();

        return h;
    }
}
