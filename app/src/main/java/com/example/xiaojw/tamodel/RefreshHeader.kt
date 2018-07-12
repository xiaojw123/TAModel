package com.example.xiaojw.tamodel

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlin.math.log

class RefreshHeader(var content: Context, var set: AttributeSet?) : FrameLayout(content, set, 0) {

    lateinit var refreshTv: TextView
    lateinit var mHeaderView: View


    constructor(content: Context) : this(content, null)


    init {
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.refresh_layout, this, true)
        refreshTv = findViewById(R.id.refresh_tv)
        APPLog.printDebug("headerView___"+mHeaderView)
        mHeaderView.layoutParams=FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,0)

    }

    fun setRefreshVisible(visibility: Int) {
        refreshTv.visibility = visibility;

    }

    var fator=150

    fun smoothScrollTo(height: Int) {


        var mH= height*fator
        APPLog.printDebug("smoothScollTo___height__"+height)
        var aim = ValueAnimator.ofInt(getVisibleHeight(),mH)
        aim.setDuration(500)
        aim.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                var lp = mHeaderView.layoutParams
                var animValue=animation?.animatedValue as Int;
                if (animValue>300){
                    animValue=300;
                }
                if (animValue<0){
                    animValue=0;
                }
                lp.height =animValue
                APPLog.printDebug("hegiht__"+lp.height)
                mHeaderView.layoutParams = lp
            }
        })
        aim.start()
    }

    fun getVisibleHeight(): Int {
        var params = mHeaderView.layoutParams
        return params.height

    }


}