package com.mingbang.mingbang.mingbang.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: zhaojy
 * @data:On 2018/1/28.
 */

public class GradualView extends View {

    private Context context;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 实心圆画笔
     */
    private Paint paintFull;
    /**
     * 标识字画笔
     */
    private Paint textPaint;
    /**
     * 移动小球画笔
     */
    private Paint valuePaint;
    /**
     * 渐变色数组
     */
    private int[] mColors;
    /**
     * 中心X
     */
    private int centerX;
    /**
     * 中心Y
     */
    private int centerY;
    /**
     * 控件高度
     */
    private int srcH;
    /**
     * 圆弧起始角度
     */
    private float startAngle = 90;
    /**
     * 圆弧所占度数
     */
    private float sweepAngle = 270;

    private float airValue = 66;

    /**
     * 直接在代码中调用时，使用该函数
     *
     * @param context
     */
    public GradualView(Context context) {
        super(context);
        initData(context);
    }

    /**
     * 在xml中使用自定义view时，使用这个函数
     *
     * @param context
     * @param attrs
     */
    public GradualView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    /**
     * 可以由上一个函数中手动调用
     *
     * @param context
     * @param atrrs
     * @param defStyle 自定义函数中的attrs表示view的属性集，defStyle表示默认的属性资源集的id
     */
    public GradualView(Context context, AttributeSet atrrs, int defStyle) {
        super(context, atrrs, defStyle);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initData(Context context) {
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        mColors = new int[]{
                0xFF660099,
                //紫色
                0xFF330033,
                //褐色
                0xFF99FF00,
                //草绿色
                0xFFFFFF00,
                //黄色
                0xFFFF6600,
                //橘色
                0xFFFF0000,
                //红色
                0xFF660099,
                //紫色

        };
        Shader s = new SweepGradient(0, 0, mColors, null);
        //设置画笔为无锯齿
        paint.setAntiAlias(true);
        //线宽
        paint.setStrokeWidth(dip2px(context, 14));

        paint.setShader(s);
        paintFull = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFull.setStyle(Paint.Style.FILL_AND_STROKE);
        //设置画笔为无锯齿
        paintFull.setAntiAlias(true);

        paintFull.setShader(s);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置字体大小
        textPaint.setTextSize(dip2px(context, 22));

        textPaint.setColor(0xFFFFFFFF);
        valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置画笔为无锯齿
        valuePaint.setAntiAlias(true);

    }

    public void setAirValue(float airValue) {
        this.airValue = airValue;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        srcH = h;
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float r = srcH / 2 - paint.getStrokeWidth() * 0.5f - dip2px(context, 200);
        //移动中心
        canvas.translate(centerX, centerY);
        @SuppressLint("DrawAllocation") RectF oval = new RectF();
        oval.left = -r;
        //左边
        oval.top = -r;
        //上边
        oval.right = r;
        //右边
        oval.bottom = r;
        //下边
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
        //绘制圆弧
        //绘制圆弧两头的小圆
        float mr = dip2px(context, 7);
        float x1 = (float) (-r * Math.sin((360 - sweepAngle) / 2 * Math.PI / 180));
        float y1 = (float) (r * Math.cos((360 - sweepAngle) / 2 * Math.PI / 180));
        canvas.drawCircle(x1, y1, mr, paintFull);
        float x2 = (float) (r * Math.sin((360 - sweepAngle) / 2 * Math.PI / 180));
        float y2 = (float) (r * Math.cos((360 - sweepAngle) / 2 * Math.PI / 180));
        canvas.drawCircle(x2, y2, mr, paintFull);
        //小圆离球的距离
        float range = r + dip2px(context, 30);
        float ar = 12f;
        //画周围小球和数字
        float ax1 = (float) (-range * Math.sin(45 * Math.PI / 180));
        float ay1 = (float) (range * Math.cos(45 * Math.PI / 180));
        canvas.drawCircle(ax1, ay1, ar, paintFull);
        canvas.drawText("0", ax1 - getTextW() * 3, ay1 + getTextH() / 2, textPaint);
        float ax2 = -range;
        float ay2 = 0;
        canvas.drawCircle(ax2, ay2, ar, paintFull);
        canvas.drawText("50", ax2 - getTextW() * 5, ay2 + getTextH() / 2, textPaint);
        float ax3 = (float) (-range * Math.sin(45 * Math.PI / 180));
        float ay3 = (float) (-range * Math.cos(45 * Math.PI / 180));
        canvas.drawCircle(ax3, ay3, ar, paintFull);
        canvas.drawText("100", ax3 - getTextW() * 7, ay3 + getTextH() / 2, textPaint);
        float ax4 = 0;
        float ay4 = -range;
        canvas.drawCircle(ax4, ay4, ar, paintFull);
        canvas.drawText("150", ax4 - getTextW() * 3, ay4 - getTextH(), textPaint);
        float ax5 = (float) (range * Math.sin(45 * Math.PI / 180));
        float ay5 = (float) (-range * Math.cos(45 * Math.PI / 180));
        canvas.drawCircle(ax5, ay5, ar, paintFull);
        canvas.drawText("200", ax5 + getTextW(), ay5 + getTextH() / 2, textPaint);
        float ax6 = range;
        float ay6 = 0;
        canvas.drawCircle(ax6, ay6, ar, paintFull);
        canvas.drawText("300", ax6 + getTextW(), ay6 + getTextH() / 2, textPaint);
        float ax7 = (float) (range * Math.sin(45 * Math.PI / 180));
        float ay7 = (float) (range * Math.cos(45 * Math.PI / 180));
        canvas.drawCircle(ax7, ay7, ar, paintFull);
        canvas.drawText("500", ax7 + getTextW(), ay7 + getTextH() / 2, textPaint);
        //画标识小球
        valuePaint.setColor(0xFFFFFFFF);
        float cx;
        float cy;
        if (airValue >= 0 && airValue <= 50) {
            cx = (float) (-r * Math.cos((45 - airValue * 0.9) * Math.PI / 180));
            cy = (float) (r * Math.sin((45 - airValue * 0.9) * Math.PI / 180));
        } else if (airValue > 50 && airValue <= 150) {
            cx = (float) (-r * Math.cos((airValue * 0.9 - 45) * Math.PI / 180));
            cy = (float) (-r * Math.sin((airValue * 0.9 - 45) * Math.PI / 180));
        } else if (airValue > 150 && airValue <= 200) {
            cx = (float) (-r * Math.cos((airValue * 0.9 - 45) * Math.PI / 180));
            cy = (float) (-r * Math.sin((airValue * 0.9 - 45) * Math.PI / 180));
        } else if (airValue > 200 && airValue <= 300) {
            cx = (float) (-r * Math.cos((airValue * 0.45 + 45) * Math.PI / 180));
            cy = (float) (-r * Math.sin((airValue * 0.45 + 45) * Math.PI / 180));
        } else if (airValue > 300 && airValue <= 500) {
            //此处有问题
            cx = (float) (r * Math.cos(((airValue - 300) * 0.225) * Math.PI / 180));
            cy = (float) (r * Math.sin(((airValue - 300) * 0.225) * Math.PI / 180));
        } else {
            cx = (float) (-r * Math.cos(45 * Math.PI / 180));
            cy = (float) (r * Math.sin(45 * Math.PI / 180));
        }
        canvas.drawCircle(cx, cy, dip2px(context, 11), valuePaint);
        canvas.drawCircle(cx, cy, dip2px(context, 4), paintFull);
    }

    /**
     * dip转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取"正"的高度
     *
     * @return
     */
    private float getTextH() {
        Paint pFont = new Paint();
        Rect rect = new Rect();
        //返回包围整个字符串的最小的一个Rect区域
        pFont.getTextBounds("正", 0, 1, rect);
        return rect.height();
    }

    /**
     * 获取"正"的宽度
     *
     * @return
     */
    private float getTextW() {
        Paint pFont = new Paint();
        Rect rect = new Rect();
        //返回包围整个字符串的最小的一个Rect区域
        pFont.getTextBounds("正", 0, 1, rect);
        return rect.width();
    }

}
