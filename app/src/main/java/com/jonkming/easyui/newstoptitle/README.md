#仿新闻顶部导航#
##目录结构##
│─ NewsTopTitleActivity.java            用于显示的Activity
│
└─ui─
 	│       BaseFlowLayout.java                 自定义的流布局
 	│       BaseNewsFragment.java               Fragment的基类
 	│       ColumnHorizontalScrollView.java     用于顶部显示标签的自定义ScrollView
 	│       MoreNewsPopupWindow.java            用于点击更多时弹出所有标签的PopupWindow
  	│       NewsFragmentPagerAdapter.java       用于显示新闻的Pager的适配器

 ##使用方法##
 将这个几个文件复制到你的工程.

 因为传进去的标题用String ,如果想修改的话。可以添加自己的实体。

 大致上需要更换的地方。

 ###NewsTopTitleActivity.java###
```
    /**
      * 顶部显示的标题
      */
     private String[] news = new String[] {
             "1","2","3","4","5","6","7","8","9","10"
    };
```

 第二处:
 因为传进BaseNewsFragment,所以这里用了Bundle,并将数据作为text 参数传进去
```
    /**
      *  初始化Fragment
      * */
     private void initFragment() {
         int count =  news.length;
         for(int i = 0; i< count;i++){
             //传递参数进去,用于测试,具体可以看BaseNewsFragment 类
             Bundle data = new Bundle();
             data.putString("text", news[i]);
             BaseNewsFragment newfragment = new BaseNewsFragment();
             newfragment.setArguments(data);
             fragments.add(newfragment);
         }
         NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
         mViewPager.setAdapter(mAdapetr);
         mViewPager.setOnPageChangeListener(pageListener);
     }
```
 修改点击事件:

```
            /**
             * 设置点击事件
             */
            localTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                }
            });
```

###MoreNewsPopupWindow.java ###
这个主要用于显示全部的标签的。主要用到了BaseFlowLayout.java 流布局,具体请看res/layout/news_top_title_more_popupwindow.xml 文件 和 BaseFlowLayout.java

由于用到了,这里就简单在构造函数里将Handler 和选中的id 传进去,以便NewsTopTitleActivity.java 调用
```
    public MoreNewsPopupWindow(Context mContext, final Handler mHandler, final int SELCT_SUCCESS , String[] mVals, int selectIndex){
        super(mContext);
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.SELCT_SUCCESS = SELCT_SUCCESS;
        this.mVals = mVals;
        this.selectIndex = selectIndex;
        mInflater = (LayoutInflater) LayoutInflater.from(mContext);
        mMenuView =  mInflater.inflate(R.layout.news_top_title_more_popupwindw,null);
        initViews();
        show();
    }

```
然后一系列的代码。这里强调的是mVals[] 数组,这个事显示标签的，如果想替换自己的实体。请更换

点击事件之后会使用消息将选中的id 传回去。
```
                tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("selectIndex",index);
                    message.setData(bundle);
                    message.what = SELCT_SUCCESS;
                    mHandler.sendMessage(message);
                    dismiss();
                }
            });
```

NewsTopTitleActivity.java
这里是将所有的View 切换,如果选中则干嘛,没有选中则干嘛
```
                    case  SWITCH_PAGE:
                    //如果PopupWindow选中了,那么就直接切换
                    int selectIndex = msg.getData().getInt("selectIndex");
                    for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (i != selectIndex)
                            localView.setSelected(false);
                        else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    break;
```

这里不说太多,详细可以看<a href ="http://blog.csdn.net/u013151336/article/details/52886585">博客</a>
当然你也可以加入这个项目....