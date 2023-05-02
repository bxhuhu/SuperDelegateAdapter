package com.cree.superdelegate.adapter.dele;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.view.DefaultViewHolder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  17:10
 *
 * @author luyongjiang
 * @version 1.0
 */
public class TypeHolderDelegate<DATA> extends BaseDelegate<DATA> {
    private LinkedHashMap<Integer, TypeDelegate<DATA>> mTypeDeles = new LinkedHashMap<>();

    public TypeHolderDelegate<DATA> injectTypeDele(TypeDelegate<DATA> typeDele) {
        typeDele.setType(mTypeDeles.size() + 1);
        mTypeDeles.put(typeDele.getType(), typeDele);
        return this;
    }

    public LinkedHashMap<Integer, TypeDelegate<DATA>> getTypeDeles() {
        return mTypeDeles;
    }

    public int onType(int position) {
        DATA data = getData().get(position);
        Set<Integer> keys = mTypeDeles.keySet();
        for (Integer key : keys) {
            if (mTypeDeles.get(key).onTypeData(data)) {
                return mTypeDeles.get(key).getType();
            }
        }
        return -1;
    }

    public BaseViewHolder<DATA> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        TypeDelegate<DATA> de = mTypeDeles.get(viewType);
        if (de == null) {
            return DefaultViewHolder.create(inflater);
        } else {
            View view = inflater.inflate(de.onLayout(), parent, false);
            return de.onCreateHolder(view);
        }
    }

    /**
     * 可以重写修改方向
     *
     * @return 默认返回的是垂直方向
     */
    public int onOrientation() {
        return LinearLayoutManager.VERTICAL;
    }


    public TypeHolderDelegate<DATA> setData(List<DATA> data) {
        super.setData(data);
        return this;
    }

    public TypeHolderDelegate<DATA> cleanAfterAddData(DATA data) {
        super.cleanAfterAddData(data);
        return this;
    }

    public TypeHolderDelegate<DATA> cleanAfterAddAllData(List<DATA> data) {
        super.cleanAfterAddAllData(data);
        return this;
    }

    public TypeHolderDelegate<DATA> clearData() {
        super.clearData();
        return this;
    }

    public TypeHolderDelegate<DATA> addData(DATA data) {
        super.addData(data);
        return this;
    }

    public TypeHolderDelegate<DATA> addAllData(List<DATA> data) {
        super.addAllData(data);
        return this;
    }

}
