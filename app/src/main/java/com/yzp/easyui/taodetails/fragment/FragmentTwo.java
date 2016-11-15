package com.yzp.easyui.taodetails.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.jonkming.easyui.R;

/**
 * 作者: yzp on 2016-09-13.
 * 邮箱: 15111424807@163.com
 * QQ: 486492302
 */
public class FragmentTwo extends Fragment {

    private View view;
    private RatingBar ratingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two,container,false);
        Instantiation();
        return view;
    }

    public void Instantiation(){

        ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //记录当前rating个数
                Log.i("kkkk","===rating==="+rating);
            }
        });
    }

}
