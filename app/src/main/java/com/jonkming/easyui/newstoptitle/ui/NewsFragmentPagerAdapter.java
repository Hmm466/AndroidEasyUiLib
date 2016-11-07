package com.jonkming.easyui.newstoptitle.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Fragment 的适配器
* @Title: NewsFragmentPagerAdapter.java
* @Package com.jonkming.easyui.newstoptitle.ui
* @author HuangMingming
* @date 2016/11/7 14:17
* @version V1.0
*/
public class NewsFragmentPagerAdapter  extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;//定义一个集合管理所有的fragment

    private FragmentManager fm;//fragment 管理器

    public NewsFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    //设置集合
    public void setFragments(ArrayList<Fragment> fragments) {
        if(this.fragments != null){
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment f : this.fragments){
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }
}
