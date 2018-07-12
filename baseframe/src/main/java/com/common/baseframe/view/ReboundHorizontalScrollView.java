package com.common.baseframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by xiaojw on 2017/12/21.
 * 弹性横向滑动
 */

public class ReboundHorizontalScrollView extends HorizontalScrollView {


    public ReboundHorizontalScrollView(Context context) {
        this(context,null);
    }

    public ReboundHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReboundHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View childView=getChildAt(0);//childView extends ViewGroup
        if (childView!=null){
            View spaceView=new View(getContext());
            spaceView.setLayoutParams(new ViewGroup.LayoutParams((r-l),1));
            addView(spaceView);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
