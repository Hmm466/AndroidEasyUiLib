package com.jonkming.easyui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jonkming.easyui.newstoptitle.NewsTopTitleActivity;

public class MainActivity extends AppCompatActivity {
    private Class[] mActivityUiClass = new Class[]{
            NewsTopTitleActivity.class
    };
    private String[] mActiviUiTitles = new String[]{
            "仿新闻顶部导航"
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
                    startActivity(new Intent(MainActivity.this,mActivityUiClass[i]));
                }
            });
            mLayout.addView(button);
        }
    }
}
