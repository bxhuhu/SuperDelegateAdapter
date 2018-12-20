# SuperDelegateAdapter
---
- 是否遇见一个适配器多种视图?
- 是否遇见一个适配器混合多种数据?
- 是否遇见一个适配器多种排列方式?
- 各种type定义的烦人?
- 各种数据转换烦死人?
- 局部数据刷新??
> 以上总总快速解决,Holder轻松复用,责任拆分,聪明的适配器.[前往博客查看详细介绍>>][1]


[![](https://jitpack.io/v/bxhuhu/SuperDelegateAdapter.svg)](https://jitpack.io/#bxhuhu/SuperDelegateAdapter)

# 引入
---
`step1:`
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
`step2:`
```gradle
dependencies {
	        compile 'com.github.bxhuhu:SuperDelegateAdapter:1.4'
	}
```
# 使用
---

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


# v1.3 添加功能
---
`除了多数据多视图混合跨列顺序加载,1.3添加单数据,多视图模式(单数据暂不支持跨列,后续完善)`


# v1.4 添加功能
---
`完善1.3中不支持的跨列,现在单数据也可以支持跨列了,只需要重写onSpanSize(),就可以完成了`




# QQ群交流
---
>`347615100`
>![Alt text](https://raw.githubusercontent.com/bxhuhu/SuperDelegateAdapter/master/Screenshots/qq.png)

[1]:https://www.jianshu.com/p/d25f5106076c