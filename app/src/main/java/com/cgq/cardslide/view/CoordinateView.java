package com.cgq.cardslide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.cgq.cardslide.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义的View  用户触摸显示十字坐标系  可根据焦点动态移动
 * Created by 55492 on 2016/12/28.
 */

public class CoordinateView extends TextView {

    private static final String TAG = "CoordinateView";
    private int winWidth, winHeight;
    /**
     * 标题栏的高度
     */
    private int actionBarHeight = -1;
    /**
     * 坐标系线的画笔
     */
    private Paint mPaint;
    /**
     * 画布
     */
    private Canvas mCanvas;
    /**
     * 用于显示当前点击的屏幕的位置的X、Y坐标
     */
    private String TextPosition = "";
    /**
     * 横纵坐标的起始位置  后面的移动坐标系  是重新传入X、Y的坐标值
     * 执行刷新
     */
    private float x = -50, y = -50;
    /**
     * 定时器  设置2秒后坐标系小时
     */
    private Timer mTimer = null;

    /**
     * 设置是否开始获取焦点显示横纵坐标
     */
    private boolean isDraw = false;


    public CoordinateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            actionBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(25);
        mPaint.setStrokeWidth(1);
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
        if (isDraw) {
            setBackgroundColor(Color.parseColor("#55000000"));
            x = 0;
            y = 0;
            invalidate();
        } else {
            setBackgroundColor(Color.parseColor("#ffffff"));
        }

    }

    public boolean isDraw() {
        return isDraw;
    }


    public void initPosition() {
        x = -50;
        y = -50;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        TextPosition = "(X = " + x + ",Y = " + y + ")";

        if (isDraw) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mOnTouchListener != null) {
                        cancelTimer();
                        mOnTouchListener.onDownTouch(x, y);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_UP:
                    if (mOnTouchListener != null) {
                        mOnTouchListener.onUpTouch(x, y);
                        createTimer();
                    }
                    break;
            }
            invalidate();
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        mCanvas.drawText(TextPosition, 0 + getPaddingLeft(), 0 + getPaddingTop() + actionBarHeight, mPaint);
        onDrawHorizontal(mCanvas, y);
        onDrawVertical(mCanvas, x);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //View在设定好布局后整个View的宽度
        winHeight = getHeight();
        winWidth = getWidth();
        //对View上的内容进行测量后得到的View内容佔据的宽度getMeasuredWidth
        Log.d(TAG, "actionBarHeight:" + actionBarHeight);
    }

    /**
     * 绘制横坐标轴 （根据Y坐标固定值，X为屏幕宽度）
     * @param canvas
     * @param y      Y坐标
     */
    private void onDrawHorizontal(Canvas canvas, float y) {
        float startX = 0, startY = y, stopX = winWidth, stopY = y;
        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }

    /**
     * 绘制纵坐标轴（根据X坐标固定值，Y为屏幕高度）
     *
     * @param canvas
     * @param x      X坐标
     */
    private void onDrawVertical(Canvas canvas, float x) {
        float startX = x, startY = 0, stopX = x, stopY = winHeight;
        canvas.drawLine(startX, startY, stopX, stopY, mPaint);
    }


    private OnTouchListener mOnTouchListener;

    public interface OnTouchListener {
        void onDownTouch(float downX, float downY);

        void onUpTouch(float upX, float upY);
    }

    public void setOnTouchListener(OnTouchListener mOnTouchListener) {
        this.mOnTouchListener = mOnTouchListener;
    }

    /**
     * 创建定时器
     * 两秒后执行一次
     */
    private void createTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        initPosition();
                    }
                });
                cancelTimer();
            }
        }, 2000, 1);
    }

    /**
     * 初始化定时器Timer
     */
    private void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
