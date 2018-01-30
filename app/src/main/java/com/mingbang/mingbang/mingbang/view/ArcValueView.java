package com.mingbang.mingbang.mingbang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author: zhaojy
 * @data:On 2018/1/28.
 */

public class ArcValueView extends View {

    private final String TAG = "ArcValueView";

    public ArcValueView(Context context) {
        super(context);
    }

    public ArcValueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                //如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {
                //如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {
                //如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
            default:
                break;
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int strokeWidth = 4;

        Paint p = new Paint();
        p.setColor(Color.rgb(17, 176, 140));
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeWidth);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int left = width / 5;
        int top = strokeWidth;
        int right = width / 5 * 4;
        int bottom = width / 5 * 3 + strokeWidth;

        float startAngle = 150f;
        float swipeAngle = 240f;

        RectF rectF = new RectF(left, top, right, bottom);
        canvas.drawArc(rectF, startAngle, swipeAngle, false, p);

        p.reset();
        p.setColor(Color.rgb(17, 176, 140));
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeWidth);
        DashPathEffect pathEffect = new DashPathEffect(new float[]{8, 16}, 1);
        p.setPathEffect(pathEffect);

        RectF rectF2 = new RectF(left + 20, top + 20, right - 20, bottom - 20);
        canvas.drawArc(rectF2, startAngle, swipeAngle, false, p);

        p.reset();
        p.setTextSize(80);
        p.setColor(Color.rgb(65, 182, 209));
        p.setTextAlign(Paint.Align.LEFT);
        p.setAntiAlias(true);
        p.setDither(true);
        Rect bounds = new Rect();
        String name = "玉灵子";
        p.getTextBounds(name, 0, name.length(), bounds);
        int l = (width - name.length() * 80) / 2;
        canvas.drawText(name, l, top + 220, p);

        Log.d(TAG, bounds.width() + "  " + l);

        String value = "754";
        p.setTextSize(120);
        p.getTextBounds(value, 0, value.length(), bounds);
        l = (width - value.length() * 120) / 2;
        canvas.drawText(value, l, top + 360, p);
        Log.d(TAG, bounds.width() + "  " + l);

    }


}
