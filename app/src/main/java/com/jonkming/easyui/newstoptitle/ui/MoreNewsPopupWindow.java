package com.jonkming.easyui.newstoptitle.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jonkming.easyui.R;

/**
 * 更多新闻的PopupWindow
* @Title: MoreNewsPopupWindow.java
* @Package com.jonkming.easyui.newstoptitle.ui
* @author HuangMingming
* @date 2016/11/7 14:14
* @version V1.0
*/
public class MoreNewsPopupWindow extends PopupWindow {
    private String[] mVals ; //如果想替换,则将这个改成自己定义的数据实体集合

    private BaseFlowLayout mFlowLayout;

    private Handler mHandler;

    private Context mContext;

    /**
     * 选择成功 发送消息的id
     */
    private int SELCT_SUCCESS = -1;

    private int selectIndex = -1;

    private LayoutInflater mInflater;

    private View mMenuView;

    public MoreNewsPopupWindow(Context mContext, final Handler mHandler, final int SELCT_SUCCESS , String[] mVals, int selectIndex){
        super(mContext);
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.SELCT_SUCCESS = SELCT_SUCCESS;
        this.mVals = mVals;
        this.selectIndex = selectIndex;
        mInflater = (LayoutInflater) LayoutInflater.from(mContext);
        mMenuView =  mInflater.inflate(R.layout.news_top_title_more_popupwindw,null);
        initViews();
        show();
    }

    /**
     * 初始化组件
     */
    private void initViews(){
        mFlowLayout = (BaseFlowLayout) mMenuView.findViewById(R.id.news_top_title_base_flowlayout);
        /**
         * 找到搜索标签的控件
         */
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.news_top_title_search_label_tv, mFlowLayout, false);
            tv.setText(mVals[i]);
            final String str = tv.getText().toString();
            final  int index = i;
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("selectIndex",index);
                    message.setData(bundle);
                    message.what = SELCT_SUCCESS;
                    mHandler.sendMessage(message);
                    dismiss();
                }
            });
            //如果当前的View 是前面选中的View  那么则将背景颜色标成其他的
            if(i == selectIndex)
                tv.setTextColor(Color.parseColor("#ffcc3131"));
            mFlowLayout.addView(tv);//添加到父View
        }

    }

    /**
     * 显示
     */
    private void show(){
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    return true;
                }catch (Exception e){
                    dismiss();

                    return true;
                }
            }
        });
    }
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, parent.getLayoutParams().width /2, 18);
        } else {
            this.dismiss();
        }
    }
}
