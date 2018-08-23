package asuper.cree.com.demo.holder;

import android.view.View;
import android.widget.TextView;

import asuper.cree.com.demo.ButterknifeHolder;
import asuper.cree.com.superdelegateadapter.R;
import butterknife.BindView;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  15:09
 *
 * @author luyongjiang
 * @version 1.0
 */
public class HeadHolder extends ButterknifeHolder<String> {

    public HeadHolder(View itemView) {
        super(itemView);
    }

    @BindView(R.id.tv_head)
    TextView tvHead;

    @Override
    protected void bindView(String s) {
        tvHead.setText("标题:" + s);
    }
}