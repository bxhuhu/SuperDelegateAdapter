#SuperDelegateAdapter
---
- 是否遇见一个适配器多种视图?
- 是否遇见一个适配器混合多种数据?
- 是否遇见一个适配器多种排列方式?
- 各种type定义的烦人?
- 各种数据转换烦死人?
- 局部数据刷新??
> 以上总总快速解决,Holder轻松复用,责任拆分,聪明的适配器.

>详细介绍[前往博客>>][1]

``` java
 mRecyclerView.setAdapter(BaseAdapter.createBaseAdapter()
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
                    @Override
                    protected int getLayoutRes() {
                        return R.layout.item_head;
                    }

                    @Override
                    protected BaseViewHolder onCreateHolder(View itemView) {
                        return new HeadHolder(itemView);
                    }

                    @Override
                    protected int onSpanSize() {
                        return 2;
                    }
                }.cleanAfterAddData("这里是头部"))
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
                    @Override
                    protected int getLayoutRes() {
                        return R.layout.item_dev;
                    }

                    @Override
                    protected int onSpanSize() {
                        return 2;
                    }

                    @Override
                    protected BaseViewHolder onCreateHolder(View itemView) {
                        return new BaseViewHolder<String>(itemView) {
                            @Override
                            protected void bindView(String str) {

                            }
                        };
                    }
                }.cleanAfterAddData("只是一个分割线"))
                .injectHolderDelegate(new CreateHolderDelegate<ContentBean>() {
                    @Override
                    protected int getLayoutRes() {
                        return R.layout.item_content;
                    }

                    @Override
                    protected BaseViewHolder onCreateHolder(View itemView) {
                        return new ContentHolder(itemView);
                    }
                }.cleanAfterAddAllData(arrays))
                .injectHolderDelegate(new CreateHolderDelegate<String>() {
                    @Override
                    protected int getLayoutRes() {
                        return R.layout.item_foot;
                    }

                    @Override
                    protected BaseViewHolder onCreateHolder(View itemView) {
                        return new BaseViewHolder<String>(itemView) {
                            @Override
                            protected void bindView(String s) {

                            }
                        };
                    }
                }.cleanAfterAddData("这是底部的View"))
                .setLayoutManager(mRecyclerView));
```
>折叠一下就是这样的了↓,是不是清晰多了.

![Alt text](https://raw.githubusercontent.com/bxhuhu/SuperDelegateAdapter/master/Screenshots/show.png)

#QQ群交流
---
>347615100

[1]:https://www.jianshu.com/p/d25f5106076c
