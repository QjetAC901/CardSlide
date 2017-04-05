package com.cgq.cardslide.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.RadioGroup;

import com.cgq.cardslide.R;

/**
 * Created by 55492 on 2017/1/4.
 *
 * 悬浮窗控件 ；
 * 可控制是否拖动、自动停靠左右边界
 */
public class FreeView extends RadioGroup {

    private static final String TAG = "FreeView";

    /**
     * 失去焦点后淡出倒计时
     */
    private int millisInFuture;
    /**
     * 淡出结束的透明度
     */
    private float toAlpha;

    /**
     * 是否允许移动
     */
    private boolean moveAble;
    /**
     * 是否允许自动返回边界
     */
    private boolean autoBack;
    /**
     * 定时器
     */
    private CustomCountDownTimer countDownTimer;

    /**
     * View的宽   右边界减左边界
     */
    private int viewWidth;
    /**
     * View的高  下边界减上边界
     */
    private int viewHeight;
    /**
     * 父布局的全部宽度  包括隐藏的
     */
    private int parentWidth;
    /**
     * 父布局的全部高度  包括隐藏的
     */
    private int parentHeight;
    /**
     * View的左边的最小Margin值
     */
    private int minLeftMargin;
    /**
     * 父布局的左边Padding值
     */
    private int leftPadding;
    /**
     * View相对于父布局的右边边界的距离
     */
    private int rightDistance;
    /**
     * 父布局对于子View的最大Margin值
     */
    private int maxLeftMargin;
    /**
     * View的TopMargin值
     */
    private int minTopMargin;
    /**
     * 父布局的TopPadding值
     */
    private int topPadding;
    /**
     * 子View距离父布局底部边界的距离
     */
    private int bottomDistance;
    /**
     * View的最大TopMargin值
     */
    private int maxTopMargin;
    //当前焦点位置
    private float currentX;
    private float currentY;

    //当前位置距离左、上边界的距离
    private int currentLeft;
    private int currentTop;

    //获取焦点最初的位置
    private float downEventPositionX;
    private float downEventPositionY;


    public FreeView(Context context) {
        this(context, null);
    }

    public FreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.free);
        millisInFuture = ta.getInt(R.styleable.free_millisInFuture, 3 * 1000);
        toAlpha = ta.getFloat(R.styleable.free_toAlpha, 0.2f);
        moveAble = ta.getBoolean(R.styleable.free_moveAble, true);
        autoBack = ta.getBoolean(R.styleable.free_autoBack, true);
        ta.recycle();
        countDownTimer = new CustomCountDownTimer(millisInFuture, 500);
        countDownTimer.start();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (moveAble) {
            ViewGroup parentView = (ViewGroup) getParent();
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            viewWidth = getRight() - getLeft();
            viewHeight = getBottom() - getTop();
            parentWidth = parentView.getMeasuredWidth();
            parentHeight = parentView.getMeasuredHeight();
            minLeftMargin = lp.leftMargin;
            leftPadding = parentView.getPaddingLeft();
            rightDistance = lp.rightMargin + parentView.getPaddingRight();
            maxLeftMargin = parentWidth - rightDistance - viewWidth - leftPadding;
            minTopMargin = lp.topMargin;
            topPadding = parentView.getPaddingTop();
            bottomDistance = lp.bottomMargin + parentView.getPaddingBottom();
            maxTopMargin = parentHeight - bottomDistance - viewHeight - topPadding;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://获得焦点
                //设置透明度为不透明
                setAlpha(1f);
                //清除定时器
                countDownTimer.cancel();
                //判断是否允许移动
                if (moveAble) {
                    //获得当前X、Y坐标和当前的左、上Margin值
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    //获取当前X、Y位置
                    currentX = ev.getRawX();
                    currentY = ev.getRawY();
                    //获取当前的左右Margin值
                    currentLeft = lp.leftMargin;
                    currentTop = lp.topMargin;
                    //赋值获得焦点的位置  在失去焦点时用于判断是否时间分发
                    downEventPositionX = ev.getRawX();
                    downEventPositionY = ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE://移动焦点
                if (moveAble) {
                    //改变左、上Margin
                    currentLeft += ev.getRawX() - currentX;
                    currentTop += ev.getRawY() - currentY;
                    //判断左边界
                    currentLeft = currentLeft < minLeftMargin ? minLeftMargin : currentLeft;
                    //再次判断左边界 （比较View的总宽度是否超出父布局的宽度）
                    currentLeft = (leftPadding + currentLeft + rightDistance + viewWidth) > parentWidth ? maxLeftMargin : currentLeft;
                    //判断上边界
                    currentTop = currentTop < minTopMargin ? minTopMargin : currentTop;
                    //再次判断上边界  （比较View的总高度是否超出父布局的高度）
                    currentTop = (topPadding + currentLeft + bottomDistance + viewHeight) > parentHeight ? maxTopMargin : currentTop;
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    lp.leftMargin = currentLeft;
                    lp.topMargin = currentTop;
                    setLayoutParams(lp);
                    currentX = ev.getRawX();
                    currentY = ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP://失去焦点
                //启动定时器 （透明化悬浮窗）
                countDownTimer.start();
                if (autoBack&&moveAble){
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    int fromLeftMargin = lp.leftMargin;
                    if (getLeft() < (parentWidth - getLeft() - viewWidth)){
                        lp.leftMargin = minLeftMargin;
                    }else{
                        lp.leftMargin = maxLeftMargin;
                    }
                    ObjectAnimator marginChange = ObjectAnimator.ofInt(new Wrapper(this), "leftMargin", fromLeftMargin, lp.leftMargin);
                    marginChange.setInterpolator(new AccelerateInterpolator(1));
                    marginChange.setDuration(50);
                    marginChange.start();
                    float x_Change = ev.getRawX() - downEventPositionX;
                    float y_Change = ev.getRawY() - downEventPositionY;
                    Log.i(TAG, "dispatchTouchEvent: x_Change:"+x_Change+"------"+y_Change);
                    if (Math.abs(x_Change)>50 || Math.abs(y_Change)>50){
                        return true;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 包装类
     */
    class Wrapper {
        private ViewGroup mTarget;

        public Wrapper(ViewGroup mTarget) {
            this.mTarget = mTarget;
        }

        public int getLeftMargin() {
            MarginLayoutParams lp = (MarginLayoutParams) mTarget.getLayoutParams();
            return lp.leftMargin;
        }

        public void setLeftMargin(int leftMargin) {
            MarginLayoutParams lp = (MarginLayoutParams) mTarget.getLayoutParams();
            lp.leftMargin = leftMargin;
            mTarget.requestLayout();
        }
    }

    /**
     * 定时器内部类
     */
    private class CustomCountDownTimer extends CountDownTimer {

        /**
         * 定时millisInFuture时间，每经过countDownInterval时间回调onFinish一次
         *
         * @param millisInFuture    执行时长
         * @param countDownInterval 每次回调onFinish的间隔
         */
        public CustomCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            setAlpha(toAlpha);
        }
    }
}
