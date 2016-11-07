package com.jonkming.easyui.newstoptitle.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonkming.easyui.R;
/**
 * 新闻Framgent的基类
* @Title: BaseNewsFragment.java
* @Package com.jonkming.easyui.newstoptitle.ui
* @author HuangMingming
* @date 2016/11/7 14:16
* @version V1.0
*/
public class BaseNewsFragment extends Fragment {
    String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        text = args != null ? args.getString("text"):"";
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_top_title_fragment,null);
        TextView item_textview = (TextView)view.findViewById(R.id.new_top_title_item_textview);
        item_textview.setText(text);
        return view;

    }
}
