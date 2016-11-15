package com.yzp.easyui.taodetails.fragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：Yzp on 2016-11-09 17:12
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class WrapContentHeightViewPager extends ViewPager {

    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childSize = getChildCount();
        int maxHeight = 0;
        for (int i = 0; i < childSize; i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            if (child.getMeasuredHeight() > maxHeight) {
                maxHeight = child.getMeasuredHeight();
            }
        }

        if (maxHeight > 0) {
            setMeasuredDimension(getMeasuredWidth(), maxHeight);
        }

    }

}
