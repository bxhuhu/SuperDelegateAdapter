package asuper.cree.com.demo;

import android.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.cree.superdelegate.adapter.dele.TypeHolderDelegate;
import com.cree.superdelegate.adapter.dele.TypeDelegate;
import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.BaseAdapter;

import java.util.ArrayList;

import asuper.cree.com.demo.bean.ContentBean;
import asuper.cree.com.demo.dele.ContentDele;
import asuper.cree.com.demo.dele.DevDele;
import asuper.cree.com.demo.dele.FootDele;
import asuper.cree.com.demo.dele.HeadDele;
import asuper.cree.com.superdelegateadapter.R;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/4/2  10:20
 *
 * @author luyongjiang
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAdapter();
    }


    private void createAdapter() {
        mRecyclerView = findViewById(R.id.rl_list);
        setTypePriority();
//        setAdapter();
    }

    /**
     * 单数据,多视图,按状态任意顺序加载适配器,暂不支持跨列,后续添加
     */
    private void setTypePriority() {
        TypeHolderDelegate<String> singleDataTypeDelegate = new TypeHolderDelegate<String>()
                .injectTypeDele(new TypeDelegate<String>() {
                    @Override
                    public boolean onTypeData(String s) {
                        return s.contains("1");
                    }

                    @Override
                    public int onLayout() {
                        return android.R.layout.simple_list_item_1;
                    }

                    @Override
                    public BaseViewHolder<String> onCreateHolder(View view) {
                        return new BaseViewHolder<String>(view) {
                            private TextView mTextView = itemView.findViewById(android.R.id.text1);

                            @Override
                            protected void bindView(String s) {
                                mTextView.setText(s);
                                mTextView.setBackgroundResource(android.R.color.holo_blue_bright);
                            }
                        };
                    }
                })
                .injectTypeDele(new TypeDelegate<String>() {
                    @Override
                    public boolean onTypeData(String s) {
                        return s.contains("2");
                    }

                    @Override
                    public int onLayout() {
                        return android.R.layout.simple_list_item_1;
                    }

                    @Override
                    public int onSpanSize() {
                        return 2;
                    }

                    @Override
                    public BaseViewHolder<String> onCreateHolder(View view) {
                        return new BaseViewHolder<String>(view) {
                            private TextView mTextView = itemView.findViewById(android.R.id.text1);

                            @Override
                            protected void bindView(String s) {
                                mTextView.setText(s);
                                mTextView.setBackgroundResource(android.R.color.holo_red_dark);
                            }
                        };
                    }
                });
        mRecyclerView.setAdapter(BaseAdapter.createBaseAdapter().enableTypePreferredModle()
                .setSingleDataTypeDelegate(singleDataTypeDelegate));
        singleDataTypeDelegate.addData("1aaa");
        singleDataTypeDelegate.addData("1aaa");
        singleDataTypeDelegate.addData("2aaa");
        singleDataTypeDelegate.addData("2aaa");
        singleDataTypeDelegate.addData("2aaa");
        singleDataTypeDelegate.addData("1bbb");
        singleDataTypeDelegate.addData("1bbb");
        singleDataTypeDelegate.addData("1bbb");
        singleDataTypeDelegate.addData("2bbb");
    }

    /**
     * 多数据,多视图,任意跨列,混合数据顺序加载适配器
     */
    private void setAdapter() {
        ArrayList<ContentBean> arrays = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrays.add(new ContentBean("i=" + i));
        }
        mRecyclerView.setAdapter(BaseAdapter.createBaseAdapter()
                .injectHolderDelegate(new HeadDele().cleanAfterAddData("这里是头部"))
                .injectHolderDelegate(new DevDele().cleanAfterAddData("只是一个分割线"))
                .injectHolderDelegate(new ContentDele().cleanAfterAddAllData(arrays))
                .injectHolderDelegate(new FootDele().cleanAfterAddData("这是底部的View")));
    }

}
