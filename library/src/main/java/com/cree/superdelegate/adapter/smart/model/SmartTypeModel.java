package com.cree.superdelegate.adapter.smart.model;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;

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

    Pair<Integer, Integer> getDelegateRangeIndex(CreateHolderDelegate delegate);
}
