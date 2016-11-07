package com.jonkming.easyui.newstoptitle.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/**
 * 自定义垂直滑动View
* @Title: ColumnHorizontalScrollView.java
* @Package com.jonkming.easyui.newstoptitle.ui
* @Description: (用一句话描述该文件做什么)
* @author HuangMingming
* @date 2016/11/7 14:14
* @version V1.0
*/
public class ColumnHorizontalScrollView  extends HorizontalScrollView {
    //传入的布局
    private View ll_content;
    //更多栏目的布局
    private View ll_more;
    //拖动栏布局
    private View rl_colnmn;
    //左阴影布局
    private ImageView leftImage;
    //右阴影布局
    private ImageView rightImage;
    //屏幕宽度
    private int mScreenWidth =0;
    //父类活动的activity
    private Activity activity;
    public ColumnHorizontalScrollView(Context context) {
        super(context);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnHorizontalScrollView(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);

    }

    /**
     * 拖动的时候执行的事件
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //先设置左右的阴影
        shade_ShowOrHide();
        if(!activity.isFinishing() && ll_content != null && leftImage!= null && rightImage != null){
            if(ll_content.getWidth() <= mScreenWidth){
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            }
        }else {
            return ;
        }
        if(l == 0){
            leftImage.setVisibility(View.GONE);
            rightImage.setVisibility(View.VISIBLE);
            return ;
        }
        if(ll_content.getWidth() - l + ll_more.getWidth() + rl_colnmn.getLeft() == mScreenWidth){
            leftImage.setVisibility(View.VISIBLE);
            rightImage.setVisibility(View.GONE);
            return ;
        }
        leftImage.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.VISIBLE);
    }

    /**
     * 传入父类的资源文件等
     * @param activity
     * @param mScreenWidth
     * @param paramView1
     * @param paramView2
     * @param paramView3
     * @param paramView4
     * @param paramView5
     */
    public void setParam(Activity activity,int mScreenWidth,View paramView1,ImageView paramView2, ImageView paramView3 ,View paramView4,View paramView5)
    {
        this.activity = activity;
        this.mScreenWidth = mScreenWidth;
        ll_content = paramView1;
        leftImage = paramView2;
        rightImage = paramView3;
        ll_more = paramView4;
        rl_colnmn = paramView5;
    }
    /**
     * 判断左右阴影显示隐藏效果
     */
    public void shade_ShowOrHide(){
        if(!activity.isFinishing() && ll_content != null){
            measure(0,0);
            //如果整体的宽度小于屏幕的宽度的话,那左右阴影都隐藏
            if(mScreenWidth >= getMeasuredWidth()){
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            }
        }else {
            return ;
        }
        //如果滑动到最左边的时候,左边阴影隐藏,右边显示
        if(getLeft() == 0){
            leftImage.setVisibility(View.GONE);
            rightImage.setVisibility(View.VISIBLE);
            return ;
        }
        //如果滑动在最右边的时候,左边阴影显示,右边隐藏
        if(getRight() == getMeasuredWidth() - mScreenWidth){
            leftImage.setVisibility(View.VISIBLE);
            rightImage.setVisibility(View.GONE);
            return ;
        }
        //否则,说明在中间位置,左右阴影都显示
        leftImage.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.VISIBLE);
    }
}
