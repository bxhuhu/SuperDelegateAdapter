package com.cree.superdelegate.adapter;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

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