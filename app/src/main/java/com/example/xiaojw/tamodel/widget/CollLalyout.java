package com.example.xiaojw.tamodel.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CollLalyout extends LinearLayout implements NestedScrollingParent{
    public CollLalyout(Context context) {
        this(context,null);
    }

    public CollLalyout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CollLalyout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes& ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {

    }
}
