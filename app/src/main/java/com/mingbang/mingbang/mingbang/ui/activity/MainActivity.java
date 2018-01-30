package com.mingbang.mingbang.mingbang.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mingbang.mingbang.mingbang.R;
import com.mingbang.mingbang.mingbang.ui.fragment.ContactsFragment;
import com.mingbang.mingbang.mingbang.ui.fragment.InforFragment;
import com.mingbang.mingbang.mingbang.ui.fragment.MyFragment;
import com.mingbang.mingbang.mingbang.ui.fragment.WorkFragment;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushServiceV3;

/**
 * @author: zhaojy
 * @data:On 2018/1/18.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";

    private TextView title;

    private Fragment inforFragment;
    private Fragment workFragment;
    private Fragment contactsFragment;
    private Fragment myFragment;

    private TextView infor;
    private TextView work;
    private TextView contacts;
    private TextView my;

    /**
     * 记录之前选择的标签
     */
    private TextView preSelectedTab;
    private Fragment preFragment;
    /**
     * 记录当前选择的标签
     */
    private TextView selectedTab;
    private Fragment curFragment;

    /**
     * 之前选中的标签的Id和当前选中的标签Id
     */
    private int preSelectedId = 0;
    private int curSelectedId = 0;

    /**
     * 记录底部标签选中和未选中的图标Id
     */
    private int[] selectImgId = {R.mipmap.selected_infor, R.mipmap.selected_work,
            R.mipmap.selected_contacts, R.mipmap.selected_my};
    private int[] unSelectImgId = {R.mipmap.infor, R.mipmap.work,
            R.mipmap.contacts, R.mipmap.my};

    /**
     * 菜单弹窗
     */
    private PopupWindow menuPop;
    private ImageView menu;

    /**
     * 按下返回键的时间
     */
    private long exitTime = 0;

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
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
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
            case R.id.infor:
                if (curSelectedId == 0) {
                    return;
                }
                preFragment = curFragment;
                preSelectedId = curSelectedId;
                preSelectedTab = selectedTab;
                if (inforFragment == null) {
                    inforFragment = new InforFragment();
                }
                curFragment = inforFragment;
                curSelectedId = 0;
                selectedTab = infor;
                footTabSelected();
                break;
            case R.id.work:
                if (curSelectedId == 1) {
                    return;
                }
                preFragment = curFragment;
                preSelectedId = curSelectedId;
                preSelectedTab = selectedTab;
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                }
                curFragment = workFragment;
                curSelectedId = 1;
                selectedTab = work;
                footTabSelected();
                break;
            case R.id.contacts:
                if (curSelectedId == 2) {
                    return;
                }
                preFragment = curFragment;
                preSelectedId = curSelectedId;
                preSelectedTab = selectedTab;
                if (contactsFragment == null) {
                    contactsFragment = new ContactsFragment();
                }
                curFragment = contactsFragment;
                curSelectedId = 2;
                selectedTab = contacts;
                footTabSelected();
                break;
            case R.id.my:
                if (curSelectedId == 3) {
                    return;
                }
                preFragment = curFragment;
                preSelectedId = curSelectedId;
                preSelectedTab = selectedTab;
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
                curFragment = myFragment;
                curSelectedId = 3;
                selectedTab = my;
                footTabSelected();
                break;
            case R.id.menu:
                if (menuPop != null && menuPop.isShowing()) {
                    menuPop.dismiss();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        openMenu();
                    }
                }
                break;
            case R.id.exitLogin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        findById();
        initPage();

        initXG("123456");
    }

    private void findById() {
        infor = findViewById(R.id.infor);
        work = findViewById(R.id.work);
        contacts = findViewById(R.id.contacts);
        my = findViewById(R.id.my);
        menu = findViewById(R.id.menu);
        title = findViewById(R.id.title);
    }


    /**
     * TODO：初始化腾讯信鸽推送功能
     */
    private void initXG(String username) {
        Context context = getApplicationContext();
        XGPushManager.registerPush(context, username,
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {

                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {

                    }
                }
        );

        Intent service = new Intent(context, XGPushServiceV3.class);
        context.startService(service);
    }

    /**
     * TODO:初始化页面
     */
    private void initPage() {
        inforFragment = new InforFragment();
        curFragment = inforFragment;
        preFragment = inforFragment;
        selectedTab = infor;
        preSelectedTab = infor;
        footTabSelected();
    }

    /**
     * TODO:初始化菜单
     */
    private void openMenu() {
        View contentView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.menu_layout, null);

        menuPop = new PopupWindow(this);
        menuPop.setContentView(contentView);

        menuPop.setWidth((int) getResources().getDimension(R.dimen.menu_w));
        menuPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        menuPop.setTouchable(true);
        menuPop.setFocusable(true);
        menuPop.setBackgroundDrawable(new BitmapDrawable());
        menuPop.setOutsideTouchable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            menuPop.showAsDropDown(menu, 8, -16, Gravity.LEFT);
        } else {
            menuPop.showAsDropDown(menu);
        }

    }

    /**
     * TODO:底部标签选择
     */
    private void footTabSelected() {
        //设置顶部标题
        if (curSelectedId == 0) {
            title.setText(getResources().getString(R.string.information));
        } else if (curSelectedId == 1) {
            title.setText(getResources().getString(R.string.work));
        } else if (curSelectedId == 2) {
            title.setText(getResources().getString(R.string.contacts));
        } else if (curSelectedId == 3) {
            title.setText(getResources().getString(R.string.my));
        }

       /* 改变底部标签字体颜色和图标 */
        preSelectedTab.setTextColor(getResources().getColor(R.color.foot_tab_txt_cl));
        selectedTab.setTextColor(getResources().getColor(R.color.theme));
        /*改变底部标签图标*/
        Drawable usDrawable = getResources().getDrawable(unSelectImgId[preSelectedId]);
        usDrawable.setBounds(0, 0, usDrawable.getMinimumWidth(),
                usDrawable.getMinimumHeight());
        preSelectedTab.setCompoundDrawables(null, usDrawable, null, null);

        Drawable sDrawable = getResources().getDrawable(selectImgId[curSelectedId]);
        sDrawable.setBounds(0, 0, sDrawable.getMinimumWidth(),
                sDrawable.getMinimumHeight());
        selectedTab.setCompoundDrawables(null, sDrawable, null, null);

         /*切换Fragment*/
        @SuppressLint("CommitTransaction")
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!curFragment.isAdded()) {
            transaction.add(R.id.content, curFragment, curFragment.getClass().getName());
        }
        transaction.hide(preFragment);
        transaction.show(curFragment);
        transaction.commit();
    }

}
