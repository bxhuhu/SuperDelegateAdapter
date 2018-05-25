package com.cree.superdelegate.adapter.smart.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  16:34
 *
 * @author luyongjiang
 * @version 1.0
 */
public interface SmartTypeModel {
    RecyclerView.LayoutManager createLayoutManager(Context context, RecyclerView.Adapter baseAdapter);

    RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    int getItemViewType(int position);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    int getItemCount();
}
