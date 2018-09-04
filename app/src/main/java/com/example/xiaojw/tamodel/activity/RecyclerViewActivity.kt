package com.example.xiaojw.tamodel.activity

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.common.baseframe.util.APPLOG
import com.example.xiaojw.tamodel.APPLog
import com.example.xiaojw.tamodel.R
import kotlin.math.log

class RecyclerViewActivity : BaseActivity() {

    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        mRecyclerView = findViewById(R.id.test_rv);
        mRecyclerView.layoutManager = LinearLayoutManager(this);
//        mRecyclerView.addItemDecoration(CustomDecoration())
        mRecyclerView.addItemDecoration(StickDecoration(object : HeaderItemCall {
            override fun getHeaderItem(positoin: Int): HeaderItem {
                val item = HeaderItem();
                item.headerId = positoin / 5
                item.position = positoin
                return item
            }
        }))
        mRecyclerView.itemAnimator=object :BaseAnmitor(){


        }
        mRecyclerView.adapter = DataAdapter(getDataList());

        val items= listOf("苹果","菠萝","木瓜")
        for (i in 0 until  items.size){



        }

    }

    open class BaseAnmitor:SimpleItemAnimator(){
        override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun runPendingAnimations() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun animateMove(holder: RecyclerView.ViewHolder?, fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun animateChange(oldHolder: RecyclerView.ViewHolder?, newHolder: RecyclerView.ViewHolder?, fromLeft: Int, fromTop: Int, toLeft: Int, toTop: Int): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isRunning(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun endAnimation(item: RecyclerView.ViewHolder?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun endAnimations() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    fun getDataList(): List<String> {
        val dataList = arrayListOf<String>()
        dataList.add("脱身")
        dataList.add("重返20岁")
        dataList.add("明日之子")
        dataList.add("阴天")
        dataList.add("哆啦A梦")
        dataList.add("你的名字")
        dataList.add("锦衣夜行")
        dataList.add("明日之子")
        dataList.add("好声音")
        dataList.add("FU")
        dataList.add("DC")
        dataList.add("AB")
        dataList.add("FN")
        dataList.add("MZ")
        dataList.add("MN")
        dataList.add("FOX")
        dataList.add("UK")
        dataList.add("ITUES")
        dataList.add("NetFix")
        dataList.add("Naver")
        dataList.add("YouTube")
        dataList.add("DailyMotion")
        dataList.add("AT&T")
        dataList.add("ESPN")
        dataList.add("Amzon")
        dataList.add("Yahoo")
        dataList.add("Ebay")
        return dataList;
    }


    class HeaderItem {
        var headerId = 0
        var position = 0;

        fun isFirstPosition(): Boolean {
            return position % 5 == 0
        }
    }

    interface HeaderItemCall {
        fun getHeaderItem(positoin: Int): HeaderItem


    }


    inner class StickDecoration(headerCall: HeaderItemCall) : RecyclerView.ItemDecoration() {


        var mHeaderHeight = 58;
        var mDividerHeight = 1;
        var mHeaderCall: HeaderItemCall
        var mTextPaint: Paint
        var mHeaderPaint: Paint

        init {
            mHeaderCall = headerCall;
            mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            mTextPaint.color = Color.GREEN
            mTextPaint.textSize = 48f
            mHeaderPaint = Paint()
            mHeaderPaint.color = resources.getColor(android.R.color.darker_gray)
        }

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            var i = parent?.getChildAdapterPosition(view);
            if (i!! % 5 == 0) {
                outRect?.set(0, mHeaderHeight, 0, 0)

            } else {
                outRect?.set(0, mDividerHeight, 0, 0)
            }
        }

//        override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
//            super.onDraw(c, parent, state)
//            for (i in 0..parent!!.childCount - 1) {
//                var itemView = parent.getChildAt(i);
//                var index = parent.getChildAdapterPosition(itemView)
//                if (index % 5 == 0) {
//                    val title = "item" + index
//                    c?.drawText(title, 20F, itemView.top.toFloat(), mTextPaint)
//                }
//            }
//
//        }

        override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.onDrawOver(c, parent, state)
            val childCount = parent?.childCount
            val left = parent?.paddingLeft
            val right = parent?.width!! - parent?.paddingRight
            for (i in 0..(childCount!! - 1)) {
                val view = parent.getChildAt(i);
                val index = parent.getChildAdapterPosition(view)
                val title = "group" + index / 5;
                if (i != 0) {

                    if (index % 5 == 0) {
                        val top = view.top - mHeaderHeight
                        val bottom = view.top
                        c?.drawRect(left!!.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mHeaderPaint)
                        c?.drawText(title, (left!! + 20).toFloat(), bottom.toFloat() - (mHeaderHeight - 24) / 2, mTextPaint)
                    }

                } else {
                    var top = parent.paddingTop
                    APPLog.printDebug("CurrentIndex____"+index)
                    if (index % 5 == 4) {//一个组内租后一个item
                        val sinleTop = view.bottom - mHeaderHeight
                        APPLog.printDebug("bottom____"+view.bottom+"___single__top__"+sinleTop)
                        if (sinleTop < top) {
                            top = sinleTop
                        }
                        APPLog.printDebug("Last__GroupItem top___"+top)
                    }
                    val bottom = top + mHeaderHeight
                    c?.drawRect(left!!.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mHeaderPaint)
                    c?.drawText(title, left!! + 20f, bottom.toFloat() - (mHeaderHeight - 24) / 2, mTextPaint)
                }


            }
        }

    }


    inner class CustomDecoration : RecyclerView.ItemDecoration() {
        var mPaint: Paint
        var mCirclePaint: Paint
        var radius = 20f

        init {
            mCirclePaint = Paint()
            mCirclePaint.color = Color.GREEN;
            mPaint = Paint()
            mPaint.color = Color.GREEN
            mPaint.strokeWidth = 10f;
        }

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            outRect?.set(70, 20, 70, 10)
        }

        override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
            c?.drawCircle(0f, 0f, 50f, mPaint)
            val left = parent!!.paddingLeft + 35
            for (i in 0..parent.childCount - 1) {
                val childView = parent.getChildAt(i)
                Log.d("Tag", "onDraw left____" + childView.left + ", right___" + childView.right + ", top___" + childView.top + ", bottom___" + childView.bottom)
                c?.drawLine(left.toFloat(), childView.top.toFloat() - 20, left.toFloat(), childView.top + childView.height / 2 - radius, mPaint)
                c?.drawLine(left.toFloat(), childView.bottom - (childView.height / 2 - radius), left.toFloat(), childView.bottom.toFloat() + 10, mPaint)
                mCirclePaint.color = Color.GREEN;
                c?.drawCircle(left.toFloat(), (childView.top + childView.height / 2).toFloat(), radius, mCirclePaint);
                mCirclePaint.color = resources.getColor(android.R.color.darker_gray)
                mCirclePaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
                c?.drawCircle(left.toFloat(), (childView.top + childView.height / 2).toFloat(), radius - 10, mCirclePaint);
            }

        }

        override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
//            c?.drawCircle(0f,0f,10f,mPaint)
            for (i in 0..(parent?.childCount!! - 1)) {
                var childView = parent.getChildAt(i);
                var bmp = BitmapFactory.decodeResource(resources, R.mipmap.timg)
                var dst = Rect(childView.left, childView.top + 25, childView.left + 200, childView.bottom - 25)
                c?.drawBitmap(bmp, null, dst, mCirclePaint)
            }
        }


    }


    class DataAdapter(items: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var mItems: List<String>

        init {

            mItems = items;


        }


        override fun getItemCount(): Int {
            return mItems.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            if (holder is DataHolder) {
                holder.dataTv.setText(mItems.get(position))
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_data_list, parent, false)
            return DataHolder(itemView);
        }
    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var dataTv: TextView

        init {
            dataTv = itemView.findViewById(R.id.data_tv);
        }

    }


}
