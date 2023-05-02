package com.cree.superdelegate.adapter.dele;

import android.view.View;

import androidx.annotation.LayoutRes;

import com.cree.superdelegate.adapter.BaseViewHolder;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  17:42
 *
 * @author luyongjiang
 * @version 1.0
 */
public interface TypeDele<DATA> {

    boolean onTypeData(DATA data);

    @LayoutRes
    int onLayout();

    BaseViewHolder<DATA> onCreateHolder(View view);
}