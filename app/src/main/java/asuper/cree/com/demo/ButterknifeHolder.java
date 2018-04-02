package asuper.cree.com.demo;

import android.view.View;

import com.cree.superdelegate.adapter.BaseViewHolder;

import butterknife.ButterKnife;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/4/2  10:24
 *
 * @author luyongjiang
 * @version 1.0
 */
public abstract class ButterknifeHolder<DATA> extends BaseViewHolder<DATA> {

    public ButterknifeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


}
