package com.example.xiaojw.tamodel.widget

import android.content.Context
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingParent
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class StickyNavLayout(context: Context, attr: AttributeSet?, defStyleAttr: Int) : LinearLayout(context), NestedScrollingParent {

    var mTopHeight = 0

    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context) : this(context, null)


    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        return true
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        var hidenTop = dy > 0 && scrollY < mTopHeight
        var showTop = dy < 0 && scrollY >= 0 && !ViewCompat.canScrollVertically(target, -1)
        if (hidenTop || showTop) {
            scrollBy(0, dy)
            consumed[1] = dy
        }

    }

    override fun onNestedFling(target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        if (target is RecyclerView&&velocityY<0){


            NestedScrollingChild

        }
        return false
    }


}