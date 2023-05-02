package com.cree.superdelegate.adapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cree.superdelegate.adapter.dele.TypeDelegate;
import com.cree.superdelegate.adapter.dele.TypeHolderDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/12/20  09:16
 *
 * @author luyongjiang
 * @version 1.0
 */
public class SingleLayoutManager extends GridLayoutManager {
    TypeHolderDelegate mTypeHolderDelegate;

    public SingleLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public SingleLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        init();
    }

    public SingleLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        init();
    }

    private void init() {
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                LinkedHashMap<Integer, TypeDelegate> typeDeles = mTypeHolderDelegate.getTypeDeles();
                Set<Integer> keys = typeDeles.keySet();
                ArrayList data = mTypeHolderDelegate.getData();
                for (Integer key : keys) {
                    boolean isCurrentData = typeDeles.get(key).onTypeData(data.get(position));
                    if (isCurrentData) {
                        return typeDeles.get(key).onSpanSize();
                    }
                }
                return 1;
            }
        });
    }

    public static SingleLayoutManager create(Context context, int orientation, TypeHolderDelegate typeHolderDelegate) {
        int spanCount = 1;
        LinkedHashMap<Integer, TypeDelegate> typeDeles = typeHolderDelegate.getTypeDeles();
        Set<Integer> keys = typeDeles.keySet();
        for (Integer key : keys) {
            if (typeDeles.get(key).onSpanSize() > spanCount) {
                spanCount = typeDeles.get(key).onSpanSize();
            }
        }
        SingleLayoutManager singleLayoutManager = new SingleLayoutManager(context, spanCount, orientation, false);
        singleLayoutManager.mTypeHolderDelegate = typeHolderDelegate;
        return singleLayoutManager;
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
