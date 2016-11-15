package com.yzp.easyui.taodetails.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonkming.easyui.R;

/**
 * 作者: yzp on 2016-09-13.
 * 邮箱: 15111424807@163.com
 * QQ: 486492302
 */
public class FragmentOne extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one,container,false);
        return view;
    }

}
