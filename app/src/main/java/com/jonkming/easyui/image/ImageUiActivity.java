package com.jonkming.easyui.image;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jonkming.easyui.MainActivity;
import com.jonkming.easyui.R;
import com.jonkming.easyui.base.BaseActivity;
import com.jonkming.easyui.image.dragzoom.ImageUi_DragZoomActivity;
import com.jonkming.easyui.image.electrocardiogram.ElectrocardiogramActivity;
import com.jonkming.easyui.newstoptitle.NewsTopTitleActivity;

/**
 * Created by Administrator on 2016/11/7.
 */

public class ImageUiActivity extends BaseActivity {
    private Class[] mActivityUiClass = new Class[]{
            ImageUi_DragZoomActivity.class, ElectrocardiogramActivity.class
    };
    private String[] mActiviUiTitles = new String[]{
            "拖动缩放图片","心电图"
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
                    startActivity(new Intent(ImageUiActivity.this,mActivityUiClass[i]));
                }
            });
            mLayout.addView(button);
        }
    }
}
