package com.cree.superdelegate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cree.superdelegate.adapter.bean.PositionBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Title:
 * Description:列表展示的适配器基类
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/3/26  10:48
 *
 * @author luyongjiang
 * @version 1.0
 */
public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CreateHolderDelegate> mDelegateHashMap = new ArrayList<>();
    private HashMap<Integer, CreateHolderDelegate> mMapDelegate = new HashMap<>();

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

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public BaseLayoutManager createLayoutManager(Context context) {
        if (mDelegateHashMap == null || mDelegateHashMap.size() == 0)
            throw new RuntimeException("请使用BaseAdapter#injectHolderDelegate方法后,再获取LayoutManager");
        return BaseLayoutManager.createBaseLayoutManager(context, mDelegateHashMap, this);
    }

    public BaseAdapter setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(createLayoutManager(recyclerView.getContext()));
        return this;
    }


    public BaseAdapter injectHolderDelegate(CreateHolderDelegate dele) {
        dele.setType(mDelegateHashMap.size() + 1);
        mDelegateHashMap.add(dele);
        mMapDelegate.put(dele.getType(), dele);
        return this;
    }

    private LayoutInflater mInflater = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        CreateHolderDelegate dele = mMapDelegate.get(viewType);
        if (dele != null) {
            return dele.onCreateHolder(mInflater.inflate(dele.getLayoutRes(), parent, false));
        }
        //如果外部没有合适的holder 调用内部默认的
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
        PositionBean indexBean = computeTruePosition(position);
        CreateHolderDelegate dele = mDelegateHashMap.get(indexBean.getIndex());
        if (dele != null) {
            return dele.getType();
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
            PositionBean positionBean = computeTruePosition(position);
            CreateHolderDelegate delegate = mDelegateHashMap.get(positionBean.getIndex());
            ((BaseViewHolder) holder).dispatchData(delegate.getData().get(positionBean.getPosition()));
        }
    }


    /**
     * 计算出当前需要得到的dele以及针对这个dele的position
     *
     * @param position
     * @return
     */
    public PositionBean computeTruePosition(int position) {
        int count = 0;
        PositionBean bean = new PositionBean();
        for (int i = 0; i < mDelegateHashMap.size(); i++) {
            CreateHolderDelegate dele = mDelegateHashMap.get(i);
            int sizeOf = dele.getData().size();
            count += sizeOf;
            if (position > count) {

            } else {
                int beforeCount = 0;
                for (int j = 0; j < i; j++) {
                    beforeCount += mDelegateHashMap.get(j).getData().size();
                }

                int truePosition = position - beforeCount;
                //---------------------------做下容错处理---------------------------------
                if (truePosition >= mDelegateHashMap.get(i).getData().size()) {
                    continue;
                }
                bean.setIndex(i);
                bean.setPosition(truePosition);
                return bean;
            }
        }
        return bean;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (CreateHolderDelegate dele : mDelegateHashMap) {
            count += dele.getData().size();
        }
        return count;
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

