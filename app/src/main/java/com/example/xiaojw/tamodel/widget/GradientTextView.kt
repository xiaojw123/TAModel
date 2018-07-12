package com.example.xiaojw.tamodel.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.R.attr.baseline
import android.graphics.Paint.FontMetricsInt



class GradientTextView(context: Context,attrs: AttributeSet?,defStyleAttr:Int):TextView(context) {


 lateinit    var mBgText:String

 lateinit var mBgPaint:Paint
 lateinit var mPrPaint:Paint
  var mBgTextSize=66f
  val mFactor=0.8f
  var mMeasureWith=0;
   var  mMearuseHeight=0



    constructor(context: Context,attrs: AttributeSet?) : this(context,attrs,0)


    constructor(context: Context):this(context,null)


    init {
        mBgText="C_START_B"
        mBgPaint=Paint()
        mBgPaint.color=Color.BLUE
        mBgPaint.textSize=mBgTextSize
        mPrPaint=Paint()
        mPrPaint.color=Color.RED
        mPrPaint.textSize=mBgTextSize




    }
    var mBaseline=0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mMeasureWith=measuredWidth
        mMearuseHeight=measuredHeight
        val fontMetrics = paint.fontMetricsInt
        mBaseline = (mMearuseHeight - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top


    }




    override fun onDraw(canvas: Canvas?) {
        canvas?.drawText(mBgText,0f, mBaseline.toFloat(),mBgPaint)


        var rectF=Rect(0,0, (mFactor*measuredWidth).toInt(), measuredHeight.toInt());
        canvas?.save()
        canvas?.clipRect(rectF)

        canvas?.drawText(mBgText,0f, mBaseline.toFloat(),mPrPaint)

        canvas?.restore()





    }




}