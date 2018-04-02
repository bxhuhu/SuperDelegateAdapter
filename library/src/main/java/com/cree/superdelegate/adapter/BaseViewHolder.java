package com.cree.superdelegate.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/3/27  18:58
 *
 * @author luyongjiang
 * @version 1.0
 */
public abstract class BaseViewHolder<DATA> extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }


    public void dispatchData(DATA data) {
        bindView(data);
    }

    protected <T extends View> T $(@IdRes int id) {
        return itemView.findViewById(id);
    }

    protected abstract void bindView(DATA data);
}