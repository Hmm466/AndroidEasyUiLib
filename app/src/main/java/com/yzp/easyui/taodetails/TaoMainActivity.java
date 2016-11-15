package com.yzp.easyui.taodetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jonkming.easyui.R;

/**
 * 作者：Yzp on 2016-11-09 17:03
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class TaoMainActivity extends Fragment {

    private View view;
    private RelativeLayout topPanel_vpg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tao_activity_main,container,false);
        Instantiation();
        return view;
    }

    public void Instantiation(){

        topPanel_vpg = (RelativeLayout) view.findViewById(R.id.topPanel_vpg);
        topPanel_vpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
