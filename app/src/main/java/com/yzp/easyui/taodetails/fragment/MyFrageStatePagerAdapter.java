package com.yzp.easyui.taodetails.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Yzp on 2016-11-09 17:13
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class MyFrageStatePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    public MyFrageStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


}
