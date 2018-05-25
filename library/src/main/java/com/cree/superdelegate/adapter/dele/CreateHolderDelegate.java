package com.cree.superdelegate.adapter.dele;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.cree.superdelegate.adapter.BaseViewHolder;

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
public abstract class CreateHolderDelegate<DATA> extends BaseDelegate<DATA> {


    private int type;

    public int getType() {
        return type;
    }

    /**
     * 不用去手动调用这个方法  会在adapter自动分配type
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }


    public CreateHolderDelegate<DATA> setData(List<DATA> data) {
        super.setData(data);
        return this;
    }

    public CreateHolderDelegate<DATA> cleanAfterAddData(DATA data) {
        super.cleanAfterAddData(data);
        return this;
    }

    public CreateHolderDelegate<DATA> cleanAfterAddAllData(List<DATA> data) {
        super.cleanAfterAddAllData(data);
        return this;
    }

    public CreateHolderDelegate<DATA> clearData() {
        super.clearData();
        return this;
    }

    public CreateHolderDelegate<DATA> addData(DATA data) {
        super.addData(data);
        return this;
    }

    public CreateHolderDelegate<DATA> addAllData(List<DATA> data) {
        super.addAllData(data);
        return this;
    }

    /**
     * 返回布局id
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutRes();


    /**
     * 获取跨列行数 现在是按顺序来的  只要在当前代理数据范围类则自动跨列
     */
    public int onSpanSize() {
        return 1;
    }


    public abstract BaseViewHolder onCreateHolder(View itemView);
}