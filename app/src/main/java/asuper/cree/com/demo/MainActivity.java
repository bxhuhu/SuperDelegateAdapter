package asuper.cree.com.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cree.superdelegate.adapter.BaseAdapter;
import com.cree.superdelegate.adapter.BaseViewHolder;
import com.cree.superdelegate.adapter.dele.CreateHolderDelegate;
import com.cree.superdelegate.adapter.dele.TypeDelegate;
import com.cree.superdelegate.adapter.dele.TypeHolderDelegate;

import java.util.ArrayList;

import asuper.cree.com.superdelegateadapter.R;
import butterknife.BindView;

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
        singleDataTypeDelegate.addData("2aaa");
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
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
                    @Override
                    public int getLayoutRes() {
                        return R.layout.item_head;
                    }

                    @Override
                    public BaseViewHolder onCreateHolder(View itemView) {
                        return new HeadHolder(itemView);
                    }

                    @Override
                    public int onSpanSize() {
                        return 2;
                    }
                }.cleanAfterAddData("这里是头部"))
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
                    @Override
                    public int getLayoutRes() {
                        return R.layout.item_dev;
                    }

                    @Override
                    public int onSpanSize() {
                        return 2;
                    }

                    @Override
                    public BaseViewHolder onCreateHolder(View itemView) {
                        return new BaseViewHolder<String>(itemView) {
                            @Override
                            protected void bindView(String str) {

                            }
                        };
                    }
                }.cleanAfterAddData("只是一个分割线"))
                .injectHolderDelegate(new CreateHolderDelegate<ContentBean>() {
                    @Override
                    public int getLayoutRes() {
                        return R.layout.item_content;
                    }

                    @Override
                    public BaseViewHolder onCreateHolder(View itemView) {
                        return new ContentHolder(itemView);
                    }
                }.cleanAfterAddAllData(arrays))
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
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
                }.cleanAfterAddData("这是底部的View")));
    }

    /**
     * 这是数据对象类, 你可以随便注入很多对象,也还是可以分段刷新
     */
    private class ContentBean {

        public ContentBean(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    class ContentHolder extends ButterknifeHolder<ContentBean> {

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

    class HeadHolder extends ButterknifeHolder<String> {

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

}
