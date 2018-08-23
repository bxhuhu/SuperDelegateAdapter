package asuper.cree.com.demo.dele;

import android.view.View;

import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;

import asuper.cree.com.superdelegateadapter.R;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  15:15
 *
 * @author luyongjiang
 * @version 1.0
 */
public class FootDele extends CreateHolderDelegate<String> {
    @Override
    public int getLayoutRes() {
        return R.layout.item_foot;
    }

    @Override
    public int onSpanSize() {
        return 2;
    }

    @Override
    public BaseViewHolder onCreateHolder(View itemView) {
        return new BaseViewHolder<String>(itemView) {
            @Override
            protected void bindView(String s) {

            }
        };
    }
}
