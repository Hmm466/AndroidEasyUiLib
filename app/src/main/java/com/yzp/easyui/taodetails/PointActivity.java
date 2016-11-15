package com.yzp.easyui.taodetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jonkming.easyui.R;
import com.yzp.easyui.taodetails.fragment.FragmentOne;
import com.yzp.easyui.taodetails.fragment.FragmentThree;
import com.yzp.easyui.taodetails.fragment.FragmentTwo;
import com.yzp.easyui.taodetails.fragment.MyFrageStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Yzp on 2016-11-09 17:10
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class PointActivity extends Fragment {

    private View view;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    //管理Fragment
    private FragmentManager fragmentManager;
    //当前页卡编号
    private int currIndex = 0;

    private MyFrageStatePagerAdapter mFragmentAdapter;
    private ViewPager ViewPage;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private LinearLayout tuwen_lin;
    private LinearLayout xinxi_lin;
    private TextView tuwen_txt;
    private TextView tuwen_txt_;
    private TextView xinxi_txt;
    private TextView xinxi_txt_;
    private LinearLayout wenti_lin;
    private TextView wenti_txt;
    private TextView wenti_txt_;
    private FragmentThree fragmentThree;
    private ScrollView scrollView_demo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.point_activity,container,false);
        //初始化TextView
        InitTextView();
        InitFragment();
        InitViewPager();
        return view;
    }

    /**
     * 初始化头标
     */
    public void InitTextView(){

        scrollView_demo = (ScrollView)getActivity().findViewById(R.id.scrollView_demo);
        tuwen_lin = (LinearLayout)getActivity().findViewById(R.id.tuwen_lin);
        xinxi_lin = (LinearLayout)getActivity().findViewById(R.id.xinxi_lin);
        wenti_lin = (LinearLayout)getActivity().findViewById(R.id.wenti_lin);
        tuwen_txt = (TextView)getActivity().findViewById(R.id.tuwen_txt);
        tuwen_txt_ = (TextView)getActivity().findViewById(R.id.tuwen_txt_);
        xinxi_txt = (TextView)getActivity().findViewById(R.id.xinxi_txt);
        xinxi_txt_ = (TextView)getActivity().findViewById(R.id.xinxi_txt_);
        wenti_txt = (TextView)getActivity().findViewById(R.id.wenti_txt);
        wenti_txt_ = (TextView)getActivity().findViewById(R.id.wenti_txt_);

        //添加点击事件
        tuwen_lin.setOnClickListener(new MyOnClickListener(0));
        xinxi_lin.setOnClickListener(new MyOnClickListener(1));
        wenti_lin.setOnClickListener(new MyOnClickListener(2));

    }

    //初始化卡片区域
    public void InitViewPager(){

        ViewPage = (ViewPager)view.findViewById(R.id.ViewPage);
        ViewPage.setAdapter( new MyFrageStatePagerAdapter(fragmentManager, mFragmentList));
        //让ViewPager缓存2个页面
        ViewPage.setOffscreenPageLimit(2);
        //设置默认打开第一页
        ViewPage.setCurrentItem(0);
        //将顶部文字恢复默认值
        resetTextViewTextColor();
        //设置viewpager页面滑动监听事件
        ViewPage.setOnPageChangeListener(new MyOnPageChangeListener());

    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    public void InitFragment(){

        mFragmentList=new ArrayList<Fragment>();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        mFragmentList.add(fragmentOne);
        mFragmentList.add(fragmentTwo);
        mFragmentList.add(fragmentThree);
        fragmentManager = getChildFragmentManager();

    }

    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {

            ViewPage.setCurrentItem(index);

        }
    }

    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    //从页卡1跳转转到页卡2
                    if(currIndex == 1){
                        tuwen_txt.setTextColor(getResources().getColor(R.color.red));
                        tuwen_txt_.setVisibility(View.VISIBLE);
                        xinxi_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        xinxi_txt_.setVisibility(View.GONE);

                    }else if(currIndex == 2){//从页卡1跳转转到页卡3
                        tuwen_txt.setTextColor(getResources().getColor(R.color.red));
                        tuwen_txt_.setVisibility(View.VISIBLE);
                        wenti_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        wenti_txt_.setVisibility(View.GONE);
                    }
                    break;
                //当前为页卡2
                case 1:
                    //从页卡2跳转转到页卡1
                    if (currIndex == 0) {
                        xinxi_txt.setTextColor(getResources().getColor(R.color.red));
                        xinxi_txt_.setVisibility(View.VISIBLE);
                        tuwen_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        tuwen_txt_.setVisibility(View.GONE);
                    } else if (currIndex == 2) { //从页卡2跳转转到页卡3
                        xinxi_txt.setTextColor(getResources().getColor(R.color.red));
                        xinxi_txt_.setVisibility(View.VISIBLE);
                        wenti_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        wenti_txt_.setVisibility(View.GONE);
                    }
                    break;
                //当前为页卡3
                case 2:
                    //从页卡3跳转转到页卡1
                    if (currIndex == 0) {
                        wenti_txt.setTextColor(getResources().getColor(R.color.red));
                        wenti_txt_.setVisibility(View.VISIBLE);
                        tuwen_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        tuwen_txt_.setVisibility(View.GONE);
                    } else if (currIndex == 1) {//从页卡3跳转转到页卡2
                        wenti_txt.setTextColor(getResources().getColor(R.color.red));
                        wenti_txt_.setVisibility(View.VISIBLE);
                        xinxi_txt.setTextColor(getResources().getColor(R.color.milk_white));
                        xinxi_txt_.setVisibility(View.GONE);
                    }
                    break;
            }
            currIndex = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){

        tuwen_txt.setTextColor(getResources().getColor(R.color.red));
        tuwen_txt_.setVisibility(View.VISIBLE);
        xinxi_txt.setTextColor(getResources().getColor(R.color.milk_white));
        xinxi_txt_.setVisibility(View.GONE);
        wenti_txt.setTextColor(getResources().getColor(R.color.milk_white));
        wenti_txt_.setVisibility(View.GONE);

    }

}
