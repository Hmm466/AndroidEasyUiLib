package com.yzp.easyui.taodetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.jonkming.easyui.R;
import com.yzp.easyui.taodetails.RefresView.PullToRefreshViewUpDown;
import com.yzp.easyui.taodetails.fragment.FragmentOne;
import com.yzp.easyui.taodetails.fragment.FragmentTwo;

import java.util.ArrayList;

/**
 * 作者：Yzp on 2016-11-09 16:49
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class FragmentShow extends FragmentActivity implements PullToRefreshViewUpDown.OnFooterRefreshListener,PullToRefreshViewUpDown.OnHeaderRefreshListener{

    private PullToRefreshViewUpDown main_pull_refresh_view_two;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private String fragment_Tag = "mainActivity";
    private int index = 0;
    private int cur;
    private ScrollView scrollView_fragment;
    private LinearLayout linmtwo;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        Instantiation();
    }

    public void Instantiation(){

        scrollView_fragment = (ScrollView) findViewById(R.id.scrollView_fragment);
        linmtwo = (LinearLayout)findViewById(R.id.linmtwo);

        main_pull_refresh_view_two = (PullToRefreshViewUpDown)findViewById(R.id.main_pull_refresh_view_two);
        main_pull_refresh_view_two.setOnHeaderRefreshListener(this);
        main_pull_refresh_view_two.setOnFooterRefreshListener(this);

        TaoMainActivity mainActivity = new TaoMainActivity();
        PointActivity pointActivity = new PointActivity();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        fragments.add(mainActivity);
        fragments.add(pointActivity);
        fragments.add(fragmentOne);
        fragments.add(fragmentTwo);


        /** 启动时,先展示热门Fragment */
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.main_layout, fragments.get(0),
                fragment_Tag);
        transaction.commit();
        linmtwo.setVisibility(View.GONE);

    }

    //上拉加载
    @Override
    public void onFooterRefresh(PullToRefreshViewUpDown view) {
        main_pull_refresh_view_two.postDelayed(new Runnable() {
            //线程
            @Override
            public void run() {
                main_pull_refresh_view_two.onFooterRefreshComplete();
                fragment_Tag = "mainActivity";
                scrollView_fragment.scrollTo(0,0);
                cur = 1;
                info(cur);
                linmtwo.setVisibility(View.VISIBLE);
            }
        },500);
    }

    //下拉刷新
    @Override
    public void onHeaderRefresh(PullToRefreshViewUpDown view) {
        main_pull_refresh_view_two.postDelayed(new Runnable() {
            //线程
            @Override
            public void run() {
                main_pull_refresh_view_two.onHeaderRefreshComplete();
                fragment_Tag = "pointActivity";
                scrollView_fragment.scrollTo(0,0);
                cur = 0;
                info(cur);
                linmtwo.setVisibility(View.GONE);
            }
        },500);
    }

    public void info(int cur){

        /**
         * 判断是否选中的是当前fragment,是则显示,不是则隐藏
         */
        if (index != cur) {
            index = cur;
            if (!fragments.get(cur).isAdded()) {//未被添加过得fragment
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction();
                transaction.setCustomAnimations(R.anim.ani_top_get_into, R.anim.ani_bottom_sign_out);
                transaction.add(R.id.main_layout, fragments.get(cur),
                        fragment_Tag);
                transaction.commit();
                for (int i = 0; i < fragments.size(); i++) {
                    if (i != cur) {
                        transaction.hide(fragments.get(i));
                    } else {
                        transaction.show(fragments.get(i));
                    }
                }
            } else {     //已被添加过得fragment
                if(cur == 1){
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();
                    transaction.setCustomAnimations(R.anim.ani_top_get_into, R.anim.ani_bottom_sign_out);
                    transaction.commit();
                    for (int i = 0; i < fragments.size(); i++) {
                        if (i != cur) {
                            transaction.hide(fragments.get(i));
                        } else {
                            transaction.show(fragments.get(i));
                        }
                    }
                }

                if (cur == 0){
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();
                    transaction.setCustomAnimations(R.anim.ani_bottom_out, R.anim.ani_top_into);
                    transaction.commit();
                    for (int i = 0; i < fragments.size(); i++) {
                        if (i != cur) {
                            transaction.hide(fragments.get(i));
                        } else {
                            transaction.show(fragments.get(i));
                        }
                    }

                }
            }
        }

    }

}
