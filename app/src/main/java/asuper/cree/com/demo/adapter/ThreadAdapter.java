package asuper.cree.com.demo.adapter;


import androidx.recyclerview.widget.RecyclerView;

import com.cree.superdelegate.adapter.BaseAdapter;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;

import java.util.ArrayList;

import asuper.cree.com.demo.bean.ContentBean;
import asuper.cree.com.demo.dele.ContentDele;
import asuper.cree.com.demo.dele.DevDele;
import asuper.cree.com.demo.dele.FootDele;
import asuper.cree.com.demo.dele.HeadDele;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  15:35
 *
 * @author luyongjiang
 * @version 1.0
 */
public class ThreadAdapter {

    private CreateHolderDelegate<ContentBean> mDele;
    private HeadDele mHeadDele;

    public void setAdapter(RecyclerView recyclerView) {
        ArrayList<ContentBean> arrays = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrays.add(new ContentBean("i=" + i));
        }
        mDele = new ContentDele().cleanAfterAddAllData(arrays);
        mHeadDele = new HeadDele();
        recyclerView.setAdapter(BaseAdapter.createBaseAdapter()
                .injectHolderDelegate(mHeadDele.cleanAfterAddData("这里是头部"))
                .injectHolderDelegate(new DevDele().cleanAfterAddData("只是一个分割线"))
                .injectHolderDelegate(mDele)
                .injectHolderDelegate(new FootDele().cleanAfterAddData("这是底部的View")));
    }

    public int getLenght() {
        return (int) (Math.random() * 10);
    }

    public void addData1() {
//        ArrayList<ContentBean> arrays = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            arrays.add(new ContentBean("战虎=" + i));
//        }
        ArrayList<String> arrays = new ArrayList<>();
        for (int i = 0; i < getLenght(); i++) {
            arrays.add("头部=" + i);
        }
        mHeadDele.cleanAfterAddAllData(arrays);
    }

    public void addData2() {
        ArrayList<ContentBean> arrays = new ArrayList<>();
        for (int i = 0; i < getLenght(); i++) {
            arrays.add(new ContentBean("歼3=" + i));
        }
        mDele.cleanAfterAddAllData(arrays);
    }
}