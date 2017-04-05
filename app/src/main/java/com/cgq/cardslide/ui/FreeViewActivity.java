package com.cgq.cardslide.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cgq.cardslide.R;
import com.cgq.cardslide.view.FreeView;
import com.cgq.cardslide.view.SwipeCaptchaView;

public class FreeViewActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FreeView mGroup;
    private TextView mContent;
    private int mode;

    private RelativeLayout rlSwipe;
    SwipeCaptchaView mSwipeCaptchaView;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_view);

        Intent intent = getIntent();
        mode = intent.getIntExtra("Mode", 0);
        initView(mode);
    }

    private void initView(int mode) {
        mGroup = (FreeView) findViewById(R.id.group);
        mContent = (TextView) findViewById(R.id.content);

        rlSwipe = (RelativeLayout) findViewById(R.id.rl_swipe);
        mSwipeCaptchaView = (SwipeCaptchaView) findViewById(R.id.swipeCaptchaView);
        mSeekBar = (SeekBar) findViewById(R.id.dragBar);
        findViewById(R.id.btnChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
            }
        });
        switch (mode) {
            case 2:
                mGroup.setVisibility(View.VISIBLE);
                mGroup.setOnCheckedChangeListener(this);
                break;
            case 3:
                rlSwipe.setVisibility(View.VISIBLE);
                mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
                    @Override
                    public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                        Toast.makeText(FreeViewActivity.this, "恭喜你啊 验证成功 可以搞事情了", Toast.LENGTH_SHORT).show();
                        //swipeCaptcha.createCaptcha();
                        mSeekBar.setEnabled(false);
                    }

                    @Override
                    public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                        Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                        Toast.makeText(FreeViewActivity.this, "你有80%的可能是机器人，现在走还来得及", Toast.LENGTH_SHORT).show();
                        swipeCaptchaView.resetCaptcha();
                        mSeekBar.setProgress(0);
                    }
                });
                mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        mSwipeCaptchaView.setCurrentSwipeValue(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //随便放这里是因为控件  (开始拖动进度条时调用)
                        mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                        mSwipeCaptchaView.matchCaptcha();
                    }
                });

                //测试从网络加载图片是否ok
                Glide.with(this)
                        .load("http://www.investide.cn/data/edata/image/20151201/20151201180507_281.jpg")
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                mSwipeCaptchaView.setImageBitmap(resource);
                                mSwipeCaptchaView.createCaptcha();
                            }
                        });
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_1:
                mContent.setText("页面1");
                break;
            case R.id.rb_2:
                mContent.setText("页面2");
                break;
            case R.id.rb_3:
                mContent.setText("页面3");
                break;
            default:
        }
    }
}
