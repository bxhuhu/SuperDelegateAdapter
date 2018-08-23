package com.cree.superdelegate.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.cree.superdelegate.adapter.bean.PositionBean;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;
import com.cree.superdelegate.adapter.smart.model.DataPreferredModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/3/26  18:43
 *
 * @author luyongjiang
 * @version 1.0
 */
public class BaseLayoutManager extends GridLayoutManager {
    ArrayList<CreateHolderDelegate> mDelegateHashMap;

    public BaseLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public BaseLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        init();
    }

    public BaseLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        init();
    }

    public void setDelegateHashMap(ArrayList<CreateHolderDelegate> delegateHashMap) {
        mDelegateHashMap = delegateHashMap;
    }

    private void init() {
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mDelegateHashMap.size() == 1) {
                    //如果代理总量为1,那就返回1,给控制列数的权限交还给跨列
                    return 1;
                }
                PositionBean positionBean = ((DataPreferredModel) ((BaseAdapter) mBaseAdapter).mModel).computeTruePosition(position);
                if (positionBean != null) {
                    return mDelegateHashMap.get(positionBean.getIndex()).onSpanSize();
                }
                return 1;
            }
        });
    }


    private RecyclerView.Adapter mBaseAdapter;

    public static BaseLayoutManager createBaseLayoutManager(Context context, ArrayList<CreateHolderDelegate> mDelegateHashMap, RecyclerView.Adapter adapter) {
        int max = 1;
        Iterator<CreateHolderDelegate> iterator = mDelegateHashMap.iterator();
        while (iterator.hasNext()) {
            CreateHolderDelegate dele = iterator.next();
            if (dele.onSpanSize() > max) {
                max = dele.onSpanSize();
            }
        }
        BaseLayoutManager manager = new BaseLayoutManager(context, max);
        manager.setDelegateHashMap(mDelegateHashMap);
        manager.mBaseAdapter = adapter;
        return manager;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
