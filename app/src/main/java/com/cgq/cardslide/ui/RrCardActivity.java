package com.cgq.cardslide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cgq.cardslide.CardConfig;
import com.cgq.cardslide.R;
import com.cgq.cardslide.bean.RrCardBean;
import com.cgq.cardslide.bean.TtCardBean;
import com.cgq.cardslide.callback.RrCallback;
import com.cgq.cardslide.callback.TtCallback;
import com.cgq.cardslide.layoutManager.OverLayCardLayoutManager;
import com.mcxtzhang.commonadapter.rv.CommonAdapter;
import com.mcxtzhang.commonadapter.rv.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RrCardActivity extends AppCompatActivity {

    private int mode;

    private RecyclerView mRv;

    private CommonAdapter<RrCardBean> mRrAdapter;
    private List<RrCardBean> rrCardData;

    CommonAdapter<TtCardBean> mTtAdapter;
    private List<TtCardBean> ttCardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rr_card);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        Intent intent = getIntent();
        if (intent != null) {
            mode = intent.getIntExtra("Mode", 2);
        }
        initView(mode);
    }

    private void initView(int mode) {
        if (mode == 0) {
            rrCardData = RrCardBean.initDatas();
            mRrAdapter = new CommonAdapter<RrCardBean>(this, rrCardData, R.layout.item_swipe_card) {


                @Override
                public void convert(ViewHolder viewHolder, RrCardBean rrCardBean) {
                    viewHolder.setText(R.id.tvName, rrCardBean.getName());
                    viewHolder.setText(R.id.tvPrecent, rrCardBean.getPostition() + " /" + mDatas.size());
                    Picasso.with(RrCardActivity.this).load(rrCardBean.getUrl()).into((ImageView) viewHolder.getView(R.id.iv));
                }
            };
            mRv.setAdapter(mRrAdapter);
            //初始化配置
            CardConfig.initConfig(this);
            ItemTouchHelper.Callback callback = new RrCallback(mRv, mRrAdapter, rrCardData);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(mRv);
        } else if (mode == 1) {
            mRv.setAdapter(mTtAdapter = new CommonAdapter<TtCardBean>(this, ttCardData = TtCardBean.initDatas(), R.layout.item_swipe_card) {

                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                }

                @Override
                public void convert(ViewHolder viewHolder, TtCardBean swipeCardBean) {
                    viewHolder.setText(R.id.tvName, swipeCardBean.getName());
                    viewHolder.setText(R.id.tvPrecent, swipeCardBean.getPostition() + " /" + mDatas.size());
                    Picasso.with(RrCardActivity.this).load(swipeCardBean.getUrl()).into((ImageView) viewHolder.getView(R.id.iv));
                }
            });
            CardConfig.initConfig(this);
            //探探上下滑是不能删除的，所以只传入左右即可
            final ItemTouchHelper.Callback callback = new TtCallback(mRv, mTtAdapter, ttCardData);
            final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(mRv);
        }
    }
}
