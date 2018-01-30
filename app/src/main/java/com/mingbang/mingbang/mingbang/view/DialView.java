package com.mingbang.mingbang.mingbang.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mingbang.mingbang.mingbang.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhaojy
 * @data:On 2018/1/28.
 */

public class DialView extends View {

    private final String TAG = "DialView";

    /**
     * 圆弧的度数
     */
    private final static float TOTAL_ANGLE = 224.0f;
    /**
     * 刻度圆弧有56个点
     */
    private final static int SCALE_COUNT = 60;

    private final static String CREDIT_LEVEL[] = {"绩效", "绩效", "绩效", "绩效", "绩效"};

    private final static String BETA = "玉灵子";

    private final static String EVALUATION_TIME = "";

    private final static int MIN_ALPHA = 0;

    private final static int MAX_ALPHA = 255;

    private final static int RED = 255;

    private final static int GREEN = 255;

    private final static int BLUE = 255;

    private final static int COLOR_TRANSPARENT = Color.argb(MIN_ALPHA, RED, GREEN, BLUE);

    private final static int COLOR_WHITE = Color.argb(MAX_ALPHA, RED, GREEN, BLUE);
    /**
     * 渐变进度圆弧的颜色
     */
    private final static int GRADIENT_COLORS[] = {COLOR_TRANSPARENT, COLOR_TRANSPARENT,
            COLOR_WHITE, COLOR_WHITE, COLOR_WHITE, COLOR_WHITE};
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 刻度圆弧的半径
     */
    private int mScaleArcRadius;
    /**
     * 刻度圆弧的宽度
     */
    private int mScaleArcWidth;
    /**
     * 进度圆弧的半径
     */
    private int mProgressArcRadius;
    /**
     * 进度圆弧的宽度
     */
    private int mProgressArcWidth;
    /**
     * 进度圆弧上的小圆点的半径
     */
    private int mBallOverstepWidth;
    /**
     * BETA的字体大小
     */
    private int mBetaTextSize;
    /**
     * 信用级别的字体大小
     */
    private int mCreditLevelTextSize;
    /**
     * 信用分数的字体大小
     */
    private int mCreditScoreTextSize;
    /**
     * 评估时间的字体大小
     */
    private int mEvaluationTimeTextSize;
    /**
     * 字上下行的间隔
     */
    private int mTextSpacing;
    /**
     * 箭头与圆弧的间隔
     */
    private int mArrowSpacing;
    /**
     * 信用分数
     */
    private int mCreditScore = 600;

    private float rotateDegrees = 202f;

    public void setmCreditScore(int mCreditScore) {
        this.mCreditScore = mCreditScore;
    }

    public int getmCreditScore() {
        return mCreditScore;
    }

    public DialView(Context context) {
        this(context, null);
    }

    public DialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    /**
     * 解析属性
     *
     * @param context      Context
     * @param attrs        AttributeSet
     * @param defStyleAttr defStyleAttr
     */
    private void parseAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray _TypedArray = context.obtainStyledAttributes(attrs, R.styleable.DialView, defStyleAttr, 0);
        mScaleArcRadius = _TypedArray.getDimensionPixelSize(R.styleable.DialView_scaleArcRadius, dp2px(context, 100));
        mScaleArcWidth = _TypedArray.getDimensionPixelSize(R.styleable.DialView_scaleArcWidth, dp2px(context, 2));
        mProgressArcRadius = _TypedArray.getDimensionPixelSize(R.styleable.DialView_progressArcRadius, dp2px(context, 105));
        mProgressArcWidth = _TypedArray.getDimensionPixelSize(R.styleable.DialView_progressArcWidth, dp2px(context, 1));
        mBetaTextSize = _TypedArray.getDimensionPixelSize(R.styleable.DialView_betaTextSize, dp2px(context, 12));
        mCreditLevelTextSize = _TypedArray.getDimensionPixelSize(R.styleable.DialView_creditLevelTextSize, dp2px(context, 18));
        mCreditScoreTextSize = _TypedArray.getDimensionPixelSize(R.styleable.DialView_creditScoreTextSize, dp2px(context, 40));
        mEvaluationTimeTextSize = _TypedArray.getDimensionPixelSize(R.styleable.DialView_evaluationTimeTextSize, dp2px(context, 12));
        mTextSpacing = _TypedArray.getDimensionPixelSize(R.styleable.DialView_textSpacing, dp2px(context, 12));
        mArrowSpacing = _TypedArray.getDimensionPixelSize(R.styleable.DialView_arrowSpacing, dp2px(context, 5));
        _TypedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);

        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.mipmap.back);
        mBallOverstepWidth = (int) Math.ceil(ball.getHeight() / 2.0 - mProgressArcWidth / 2.0);

        if (widthmode != MeasureSpec.EXACTLY) {
            int width = Math.max(mScaleArcRadius, mProgressArcRadius) * 2 + mBallOverstepWidth * 2
                    + getPaddingLeft() + getPaddingRight();
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        if (heightmode != MeasureSpec.EXACTLY) {
            int maxradius = Math.max(mScaleArcRadius, mProgressArcRadius);
            int height = (int) (maxradius + maxradius * Math.sin(Math.toRadians(22.5))
                    + mBallOverstepWidth + ball.getHeight() / 2 + getPaddingTop() + getPaddingBottom());
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int maxradius = Math.max(mProgressArcRadius, mScaleArcRadius);
        //移动原点到中心位置
        canvas.translate(maxradius + mBallOverstepWidth, maxradius + mBallOverstepWidth);
        //画底层圆弧
        drawProgressArc(canvas);
        //画渐变的进度画弧
        //  drawGradientProgressArc(canvas);
        //画进度圆弧上的小球
        //   drawProgressArcBall(canvas);
        //画刻度圆弧
        drawScaleArc(canvas, 80, SCALE_COUNT);
        //画箭头
        drawArrow(canvas);
        //画文字
        drawText(canvas);

    }

    /**
     * 画底层圆弧
     *
     * @param canvas Canvas
     */
    private void drawProgressArc(Canvas canvas) {
        canvas.save();
        canvas.rotate(-rotateDegrees);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressArcWidth);
        mPaint.setColor(getResources().getColor(R.color.white));
        float progressarcl = mProgressArcRadius - mProgressArcWidth / 2.0f;
        RectF progressarcrectf = new RectF(-progressarcl, -progressarcl,
                progressarcl, progressarcl);
        canvas.drawArc(progressarcrectf, 0, TOTAL_ANGLE, false, mPaint);
        canvas.restore();
    }

    /**
     * 画渐变的进度画弧
     *
     * @param canvas Canvas
     */
    private void drawGradientProgressArc(Canvas canvas) {
        canvas.save();
        canvas.rotate(-rotateDegrees);
        mPaint.setStrokeWidth(mProgressArcWidth);
        SweepGradient shader = new SweepGradient(0, 0, GRADIENT_COLORS, null);
        mPaint.setShader(shader);
        mPaint.setColor(getResources().getColor(R.color.white));
        float targetangle = getTargetAngle(mCreditScore);
        float progressarcl = mProgressArcRadius - mProgressArcWidth / 2.0f;
        RectF progressarcrectf = new RectF(-progressarcl, -progressarcl,
                progressarcl, progressarcl);
        canvas.drawArc(progressarcrectf, 0, targetangle, false, mPaint);
        mPaint.setShader(null);
        canvas.restore();
    }

    /**
     * 画进度圆弧上的小球
     *
     * @param canvas Canvas
     */
    private void drawProgressArcBall(Canvas canvas) {
        canvas.save();
        float targetangle = getTargetAngle(mCreditScore);
        canvas.rotate(targetangle - 202.5f);
        mPaint.setStyle(Paint.Style.FILL);
        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.mipmap.back);
        canvas.drawBitmap(ball, mProgressArcRadius - ball.getHeight() / 2.0f, -(ball.getWidth() / 2.0f), mPaint);
        canvas.restore();
    }

    /**
     * 画刻度圆弧
     *
     * @param canvas Canvas
     * @param pAlpha 没有到达的透明度 0~255
     * @param pCount 点的个数
     */
    private void drawScaleArc(Canvas canvas, int pAlpha, int pCount) {
        canvas.save();
        canvas.rotate(-rotateDegrees);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setStrokeWidth(mScaleArcWidth);
        float scalearcl = mScaleArcRadius - mScaleArcWidth / 2.0f;
        RectF scalearcrectf = new RectF(-scalearcl, -scalearcl, scalearcl, scalearcl);
        //画完每个刻度所要旋转的度数
        float dialspacing = TOTAL_ANGLE / pCount;
        float targetangle = getTargetAngle(mCreditScore);

        float currentangle = 270f - TOTAL_ANGLE / 2;
        boolean setalpha = false;
        for (int i = 0; i < pCount; i++) {
            if (currentangle > targetangle && !setalpha) {
                //设置未达到的点的透明度
                mPaint.setAlpha(pAlpha);
                setalpha = true;
            }
            canvas.drawArc(scalearcrectf, 0, 1, false, mPaint);
            canvas.rotate(dialspacing);
            currentangle += dialspacing;
        }
        //恢复透明度
        mPaint.setAlpha(255);
        canvas.restore();
    }

    /**
     * 画箭头
     *
     * @param canvas Canvas
     */
    private void drawArrow(Canvas canvas) {
        canvas.save();
        float targetangle = getTargetAngle(mCreditScore);
        canvas.rotate(targetangle);
        mPaint.setStyle(Paint.Style.FILL);
        Bitmap arrow = BitmapFactory.decodeResource(getResources(), R.mipmap.pointer);
        int minradius = Math.min(mProgressArcRadius, mScaleArcRadius);
        float left;
        if (minradius == mScaleArcRadius) {
            left = mScaleArcRadius - mScaleArcWidth / 2 - mArrowSpacing - arrow.getWidth();
        } else {
            left = mProgressArcRadius - mProgressArcWidth / 2 - mArrowSpacing - arrow.getWidth();
        }
        float top = -(arrow.getHeight() / 2.0f);
        canvas.drawBitmap(arrow, left, top, mPaint);
        canvas.restore();
    }

    /**
     * 画文字
     *
     * @param canvas Canvas
     */
    private void drawText(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.white));

        //信用分数
        mPaint.setTextSize(mCreditScoreTextSize);
        String creditscore = String.valueOf(mCreditScore);
        float width = mPaint.measureText(creditscore);
        Rect rect = new Rect();
        mPaint.getTextBounds(creditscore, 0, creditscore.length(), rect);
        float y = 0;
        canvas.drawText(creditscore, -(width / 2.0f), y, mPaint);

        //信用级别
        y = y - rect.height() - mTextSpacing;
        mPaint.setTextSize(mCreditLevelTextSize);
        String creditlevel = getCreditLevel(mCreditScore);
        width = mPaint.measureText(creditlevel);
        mPaint.getTextBounds(creditlevel, 0, creditlevel.length(), rect);
        canvas.drawText(creditlevel, -(width / 2.0f), y, mPaint);

        //BETA
        y = y - rect.height() - mTextSpacing;
        mPaint.setTextSize(mBetaTextSize);
        mPaint.setAlpha(220);
        width = mPaint.measureText(BETA);
        canvas.drawText(BETA, -(width / 2.0f), y, mPaint);

        //评估时间
        String evaluationtime = EVALUATION_TIME + getDate();
        width = mPaint.measureText(evaluationtime);
        Log.d(TAG, width + "");
        mPaint.getTextBounds(evaluationtime, 0, evaluationtime.length(), rect);
        mPaint.setTextSize(mEvaluationTimeTextSize);
        y = mTextSpacing + rect.height();
        canvas.drawText(evaluationtime, -(width / 2.0f), y, mPaint);
    }

    /**
     * dp转px
     *
     * @param pContext Context
     * @param pDpVal   dp值
     * @return px值
     */
    private static int dp2px(Context pContext, int pDpVal) {
        float _Scale = pContext.getResources().getDisplayMetrics().density;
        return (int) (pDpVal * _Scale + 0.5f * (pDpVal >= 0 ? 1 : -1));
    }

    /**
     * 根据信用分数计算出目标角度
     *
     * @param pCreditScore 信用分数
     * @return 目标角度
     */
    private float getTargetAngle(float pCreditScore) {
        //最大分值
        float maxScore = 1200f;
        //刻度的起始角度
        float startAngle = 270f - TOTAL_ANGLE / 2;
        //占比
        float ration = pCreditScore / maxScore;
        float targetAngle = startAngle + ration * TOTAL_ANGLE;

        return targetAngle;
    }

    /**
     * 根据信用分数获取信用级别
     *
     * @param pCreditScore 信用分数
     * @return 信用级别
     */
    private String getCreditLevel(int pCreditScore) {
        if (pCreditScore >= 350 && pCreditScore < 550) {
            return CREDIT_LEVEL[0];
        } else if (pCreditScore >= 550 && pCreditScore < 600) {
            return CREDIT_LEVEL[1];
        } else if (pCreditScore >= 600 && pCreditScore < 650) {
            return CREDIT_LEVEL[2];
        } else if (pCreditScore >= 650 && pCreditScore < 700) {
            return CREDIT_LEVEL[3];
        } else if (pCreditScore >= 700 && pCreditScore <= 950) {
            return CREDIT_LEVEL[4];
        } else {
            return CREDIT_LEVEL[0];
        }
    }

    /**
     * 获取yyyy-MM-dd格式的日期
     *
     * @return yyyy-MM-dd
     */
    private String getDate() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
        return dateformat.format(new Date());
    }

}