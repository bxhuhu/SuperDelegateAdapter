package com.cree.superdelegate.adapter.dele;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  17:07
 *
 * @author luyongjiang
 * @version 1.0
 */
class BaseDelegate<DATA> {
    private ArrayList<DATA> mData = new ArrayList<>();

    public ArrayList<DATA> getData() {
        return mData;
    }


    public void setData(ArrayList<DATA> data) {
        mData = data;
    }

    public BaseDelegate<DATA> setData(List<DATA> data) {
        mData.clear();
        mData.addAll(data);
        return this;
    }

    public BaseDelegate<DATA> cleanAfterAddData(DATA data) {
        mData.clear();
        mData.add(data);
        return this;
    }

    public BaseDelegate<DATA> cleanAfterAddAllData(List<DATA> data) {
        mData.clear();
        mData.addAll(data);
        return this;
    }

    public BaseDelegate<DATA> clearData() {
        mData.clear();
        return this;
    }

    public BaseDelegate<DATA> addData(DATA data) {
        mData.add(data);
        return this;
    }

    public BaseDelegate<DATA> addAllData(List<DATA> data) {
        mData.addAll(data);
        return this;
    }


}
