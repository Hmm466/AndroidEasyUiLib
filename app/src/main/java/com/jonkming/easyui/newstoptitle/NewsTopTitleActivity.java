package com.jonkming.easyui.newstoptitle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jonkming.easyui.R;
import com.jonkming.easyui.base.BaseFragmentActivity;
import com.jonkming.easyui.newstoptitle.ui.BaseNewsFragment;
import com.jonkming.easyui.newstoptitle.ui.ColumnHorizontalScrollView;
import com.jonkming.easyui.newstoptitle.ui.MoreNewsPopupWindow;
import com.jonkming.easyui.newstoptitle.ui.NewsFragmentPagerAdapter;

import java.util.ArrayList;


/**
 *
 * 新闻顶部导航栏
* @Title: NewsTopTitleActivity.java
* @Package com.jonkming.easyui.newstoptitle
* @Description:
* @author HuangMingming
* @date 2016/11/7 14:12
* @version V1.0
*/
public class NewsTopTitleActivity extends BaseFragmentActivity {
    /**自定义的HorizontalScrollView**/
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;

    private LinearLayout mRadioGroup_content;

    private  LinearLayout ll_more_columns;

    private RelativeLayout rl_colum;

    private ViewPager mViewPager;

    private ImageView button_more_columns;
    /**
     * 顶部显示的标题
     */
    private String[] news = new String[] {
            "1","2","3","4","5","6","7","8","9","10"
    };
    //当前选中的栏目
    private int colnmuSelectIndex = 0;
    //左阴影部分
    public ImageView shade_left;
    //右阴影部分
    public ImageView shade_right;
    //屏幕宽度
    private int mScreenWidth = 0;

    //Item 的高度
    private int mItemWidth = 0;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actiivty_news_top_title);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        intiViews();
    }
    private final int SWITCH_PAGE =  0x00123;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
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
            }
        }
    };
    /**
     * 初始化组件
     */
    protected void intiViews() {
        mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)findViewById(R.id.news_top_title_column_horizontal_scrollview);
        mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
        rl_colum = (RelativeLayout) findViewById(R.id.rl_column);
        button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        shade_left = (ImageView) findViewById(R.id.shade_left);
        shade_right = (ImageView) findViewById(R.id.shade_right);
        button_more_columns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MoreNewsPopupWindow morePopWindow = new MoreNewsPopupWindow(NewsTopTitleActivity.this,mHandler,SWITCH_PAGE,news,colnmuSelectIndex);
                morePopWindow.showPopupWindow(button_more_columns);
            }
        });
        setChangelView();
    }
    /**
     *  当栏目项发生变化时候调用
     * */
    private void setChangelView() {
        // initColumnData();
        initTabColumn();
        initFragment();
    }
    /**
     *  初始化Column栏目项
     * */
    private void initTabColumn() {
        //先删除所有的控件
        mRadioGroup_content.removeAllViews();
        //获取所有的结合
        int count =  news.length;
        //设置自定义的所有布局
        mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_colum);
        for(int i = 0; i< count; i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , ViewPager.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            params.rightMargin = 10;
            TextView localTextView = new TextView(this);
            localTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
            localTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setPadding(5, 0, 5, 0);
            localTextView.setId(i);
            localTextView.setText(news[i]);
            localTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if(colnmuSelectIndex == i){
                localTextView.setSelected(true);
            }
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
            mRadioGroup_content.addView(localTextView, i ,params);
        }
    }
    /**
     *  选择的Column里面的Tab
     * */
    private void selectTab(int tab_postion) {
        colnmuSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }
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
    /**
     *  ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener= new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };
}
