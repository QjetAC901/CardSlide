package com.cgq.cardslide.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by 55492 on 2017/3/31.
 */

public class DiffCallBack extends DiffUtil.Callback{

    private List<String> oldData;
    private List<String> newData;

    public DiffCallBack(List<String> oldData, List<String> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override//比较两个Item布局是否想听  true：相同    false：不相同  将不会执行areContentsTheSame（）
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override//判断数据是否相同   true：不刷新    false：轻量级刷新
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Nullable
    @Override//返回一个代表着新老Item的改变内容的Payload对象
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
