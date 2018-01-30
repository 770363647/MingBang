package com.mingbang.mingbang.mingbang.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.commit451.nativestackblur.NativeStackBlur;
import com.mingbang.mingbang.mingbang.R;

/**
 * @author: zhaojy
 * @data:On 2018/1/19.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
    }

    /**
     * TODO：设置状态栏颜色
     */
    private void setStatusBar() {
        Window window = this.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.theme));
        }

    }

    /**
     * TODO:设置状态栏透明
     */
    public void setStatusBarTranparent() {
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * TODO:模糊处理
     */
    public void dimOption(ImageView view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //截取区域视图
        Bitmap bitmap = view.getDrawingCache();
        int x = (int) view.getX();
        int y = (int) view.getY();

        Bitmap tempBitmap = null;
        //模糊处理
        if (bitmap != null) {
            int bitmapX = bitmap.getWidth();
            int bitmapY = bitmap.getHeight();
            tempBitmap = Bitmap.createBitmap(bitmap, x, y, bitmapX, bitmapY);
            blur(tempBitmap, view, 12);
            tempBitmap.recycle();
        }
        //清除缓存
        view.setDrawingCacheEnabled(false);
    }

    private void blur(Bitmap bkg, ImageView view, float radius) {
        int scaleFactor = 1;
        Bitmap overlay = null;

        if (overlay != null) {
            overlay.recycle();
        }
        overlay = Bitmap.createScaledBitmap(bkg, bkg.getWidth() / scaleFactor, bkg.getHeight() / scaleFactor, false);
        //高斯模糊
        overlay = NativeStackBlur.process(overlay, (int) radius);
        view.setImageBitmap(overlay);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return 返回状态栏高度
     */
    public double getStatusBarHeight(Context context) {
        double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);
        return statusBarHeight;
    }
}
