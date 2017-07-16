package com.jonkming.easyui.hardware;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jonkming.easyui.R;
import com.jonkming.easyui.base.BaseActivity;

/**
 * Package:com.jonkming.easyui.hardware
 * Created by ming on 2017/7/16.14:07
 * Mail:mailhuangmingming@163.com
 */

public class HardWareActivity extends BaseActivity
{
    private Class[] mActivityUiClass = new Class[]{
            HardWareActivity.class

    };
    private String[] mActiviUiTitles = new String[]{
            "指南针效果","GPS效果","智能穿戴(Ble)","小米手环测试"
    };
    private LinearLayout mLayout ;

    private int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.main_activity_linelayout);
        for( index = 0; index < mActiviUiTitles.length;index++){
            final int i = index;
            Button button = new Button(getApplicationContext());
            button.setTextColor(Color.BLACK);
            button.setText(mActiviUiTitles[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HardWareActivity.this,mActivityUiClass[i]));
                }
            });
            mLayout.addView(button);
        }
        initToolbar("硬件操作集合","",true);
    }
}
