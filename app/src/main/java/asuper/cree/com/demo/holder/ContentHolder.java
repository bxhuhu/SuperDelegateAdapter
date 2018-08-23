package asuper.cree.com.demo.holder;

import android.view.View;
import android.widget.TextView;

import asuper.cree.com.demo.ButterknifeHolder;
import asuper.cree.com.demo.bean.ContentBean;
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
public class ContentHolder extends ButterknifeHolder<ContentBean> {

    public ContentHolder(View itemView) {
        super(itemView);
    }

    @BindView(R.id.tv_name)
    public TextView tvName;

    @Override
    protected void bindView(ContentBean bean) {
        tvName.setText(bean.getName());
    }
}