package asuper.cree.com.demo.dele;

import android.view.View;

import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;

import asuper.cree.com.demo.bean.ContentBean;
import asuper.cree.com.demo.holder.ContentHolder;
import asuper.cree.com.superdelegateadapter.R;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  15:14
 *
 * @author luyongjiang
 * @version 1.0
 */
public class ContentDele extends CreateHolderDelegate<ContentBean> {
    @Override
    public int getLayoutRes() {
        return R.layout.item_content;
    }

    @Override
    public BaseViewHolder onCreateHolder(View itemView) {
        return new ContentHolder(itemView);
    }
}