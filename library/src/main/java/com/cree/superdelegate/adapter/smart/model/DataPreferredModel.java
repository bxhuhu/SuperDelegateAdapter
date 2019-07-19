package com.cree.superdelegate.adapter.smart.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cree.superdelegate.adapter.BaseLayoutManager;
import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;
import com.cree.superdelegate.adapter.bean.PositionBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Title:
 * Description:数据优先模式
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  16:34
 *
 * @author luyongjiang
 * @version 1.0
 */
public class DataPreferredModel extends BaseTypeModel {

    private ArrayList<CreateHolderDelegate> mDelegateHashMap = new ArrayList<>();
    private HashMap<Integer, CreateHolderDelegate> mMapDelegate = new HashMap<>();


    @Override
    public RecyclerView.LayoutManager createLayoutManager(Context context, RecyclerView.Adapter adapter) {
        if (mDelegateHashMap == null || mDelegateHashMap.size() == 0)
            throw new RuntimeException("请使用BaseAdapter#injectHolderDelegate方法后,再获取LayoutManager");
        return BaseLayoutManager.createBaseLayoutManager(context, mDelegateHashMap, adapter);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        CreateHolderDelegate dele = mMapDelegate.get(viewType);
        if (dele != null) {
            return dele.onCreateHolder(inflater.inflate(dele.getLayoutRes(), parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        PositionBean indexBean = computeTruePosition(position);
        CreateHolderDelegate dele = mDelegateHashMap.get(indexBean.getIndex());
        if (dele != null) {
            return dele.getType();
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PositionBean positionBean = computeTruePosition(position);
        CreateHolderDelegate delegate = mDelegateHashMap.get(positionBean.getIndex());
        ((BaseViewHolder) holder).dispatchData(delegate.getData().get(positionBean.getPosition()));
    }

    @Override
    public int getItemCount() {
        int count = 0;

        for (CreateHolderDelegate dele : mDelegateHashMap) {
            int deleCount = 0;
            if (dele.getData() != null) {
                deleCount = dele.getData().size();
            }
            count += deleCount;
        }
        return count;
    }

    public void injectHolderDelegate(CreateHolderDelegate dele) {
        dele.setType(mDelegateHashMap.size() + 1);
        mDelegateHashMap.add(dele);
        mMapDelegate.put(dele.getType(), dele);
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

}
