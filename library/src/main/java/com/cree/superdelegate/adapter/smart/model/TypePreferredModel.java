package com.cree.superdelegate.adapter.smart.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.SingleLayoutManager;
import com.cree.superdelegate.adapter.dele.TypeHolderDelegate;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  17:02
 *
 * @author luyongjiang
 * @version 1.0
 */
public class TypePreferredModel extends BaseTypeModel {
    private TypeHolderDelegate mTypeHolderDelegate = null;

    @Override
    public RecyclerView.LayoutManager createLayoutManager(Context context, RecyclerView.Adapter baseAdapter) {
        return SingleLayoutManager.create(context, mTypeHolderDelegate.onOrientation(), mTypeHolderDelegate);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return mTypeHolderDelegate.onCreateViewHolder(inflater, parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return mTypeHolderDelegate.onType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).dispatchData(mTypeHolderDelegate.getData().get(position));
    }


    @Override
    public int getItemCount() {
        return mTypeHolderDelegate.getData().size();
    }

    /**
     * 这个代理器只有一个,因为这种模式需要限制数据只有一种
     */
    public void injectTypeHolder(TypeHolderDelegate typeHolderDelegate) {
        mTypeHolderDelegate = typeHolderDelegate;
    }

}
