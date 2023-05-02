package com.cree.superdelegate.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.cree.superdelegate.BuildConfig;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;
import com.cree.superdelegate.adapter.smart.model.DataPreferredModel;
import com.cree.superdelegate.adapter.smart.model.SmartTypeModel;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  16:51
 *
 * @author luyongjiang
 * @version 1.0
 */

class SmartBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public SmartTypeModel mModel = new DataPreferredModel();

    public static BaseAdapter createBaseAdapter() {
        return new BaseAdapter();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() == null) {
            setLayoutManager(recyclerView);
        }
    }

    public RecyclerView.LayoutManager createLayoutManager(Context context) {
        return mModel.createLayoutManager(context, this);
    }

    public SmartBaseAdapter setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(createLayoutManager(recyclerView.getContext()));
        return this;
    }


    public SmartBaseAdapter injectHolderDelegate(CreateHolderDelegate dele) {
        if (mModel instanceof DataPreferredModel) {
            ((DataPreferredModel) mModel).injectHolderDelegate(dele);
        } else {
            if (BuildConfig.DEBUG) {
                Log.e("cree", "当前不是数据优先模式,添加数据代理失败");
            }
        }
        return this;
    }


    private LayoutInflater mInflater = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        //如果外部没有合适的holder 调用内部默认的
        RecyclerView.ViewHolder holder = mModel.onCreateViewHolder(mInflater, parent, viewType);
        if (holder != null) {
            return holder;
        }
        return createViewHolder(parent);
    }

    /**
     * 返回默认的Adapter
     *
     * @param parent
     * @return
     */
    private BaseViewHolder createViewHolder(ViewGroup parent) {
        return new DefaultViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        int type;

        if ((type = mModel.getItemViewType(position)) != -1) {
            return type;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null)
            //如果为空也什么都不做
            return;
        if (holder instanceof DefaultViewHolder) {
            //如果是默认视图 则什么都不做
            return;
        }
        if (holder instanceof BaseViewHolder) {
            mModel.onBindViewHolder(holder, position);
        }
    }


    @Override
    public int getItemCount() {
        return mModel.getItemCount();
    }

    public int getFootPosition() {
        return getItemCount() - 1;
    }

    private static class DefaultViewHolder extends BaseViewHolder<String> {

        public DefaultViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(String s) {

        }
    }


}
