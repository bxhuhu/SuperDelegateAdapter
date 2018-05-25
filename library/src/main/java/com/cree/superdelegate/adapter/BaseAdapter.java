package com.cree.superdelegate.adapter;

import android.support.v7.widget.RecyclerView;

import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;
import com.cree.superdelegate.adapter.dele.TypeHolderDelegate;
import com.cree.superdelegate.adapter.smart.model.TypePreferredModel;

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
public class BaseAdapter extends SmartBaseAdapter {

    @Override
    public BaseAdapter injectHolderDelegate(CreateHolderDelegate dele) {
        super.injectHolderDelegate(dele);
        return this;
    }

    @Override
    public BaseAdapter setLayoutManager(RecyclerView recyclerView) {
        super.setLayoutManager(recyclerView);
        return this;
    }


    public BaseAdapter setSingleDataTypeDelegate(TypeHolderDelegate singleDataTypeDelegate) {
        ((TypePreferredModel) mModel).injectTypeHolder(singleDataTypeDelegate);
        return this;
    }

    /**
     * 启用类型优先模式
     *
     * @return
     */
    public BaseAdapter enableTypePreferredModle() {
        mModel = new TypePreferredModel();
        return this;
    }

}

