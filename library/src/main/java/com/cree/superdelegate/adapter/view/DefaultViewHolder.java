package com.cree.superdelegate.adapter.view;

import android.view.LayoutInflater;
import android.view.View;

import com.cree.superdelegate.adapter.BaseViewHolder;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/27  14:24
 *
 * @author luyongjiang
 * @version 1.0
 */
public class DefaultViewHolder<DATA> extends BaseViewHolder<DATA> {
    public DefaultViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindView(DATA data) {

    }

    public static <DATA> DefaultViewHolder<DATA> create(LayoutInflater inflater) {
        return new DefaultViewHolder<>(inflater.inflate(android.R.layout.simple_list_item_1, null, false));
    }
}
