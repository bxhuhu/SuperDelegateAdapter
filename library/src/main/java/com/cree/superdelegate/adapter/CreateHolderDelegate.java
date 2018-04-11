package com.cree.superdelegate.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/3/27  18:57
 *
 * @author luyongjiang
 * @version 1.0
 */
public abstract class CreateHolderDelegate<DATA> {

    private ArrayList<DATA> mData = new ArrayList<>();

    public ArrayList<DATA> getData() {
        return mData;
    }

    private int type;


    protected int getType() {
        return type;
    }

    /**
     * 不用去手动调用这个方法  会在adapter自动分配type
     *
     * @param type
     */
    protected void setType(int type) {
        this.type = type;
    }

    public void setData(ArrayList<DATA> data) {
        mData = data;
    }

    public CreateHolderDelegate<DATA> setData(List<DATA> data) {
        mData.clear();
        mData.addAll(data);
        return this;
    }

    public CreateHolderDelegate<DATA> cleanAfterAddData(DATA data) {
        mData.clear();
        mData.add(data);
        return this;
    }

    public CreateHolderDelegate<DATA> cleanAfterAddAllData(List<DATA> data) {
        mData.clear();
        mData.addAll(data);
        return this;
    }

    public CreateHolderDelegate<DATA> clearData() {
        mData.clear();
        return this;
    }

    public CreateHolderDelegate<DATA> addData(DATA data) {
        mData.add(data);
        return this;
    }

    public CreateHolderDelegate<DATA> addAllData(List<DATA> data) {
        mData.addAll(data);
        return this;
    }

    /**
     * 返回布局id
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();


    /**
     * 获取跨列行数 现在是按顺序来的  只要在当前代理数据范围类则自动跨列
     */
    protected int onSpanSize() {
        return 1;
    }


    protected abstract BaseViewHolder onCreateHolder(View itemView);
}