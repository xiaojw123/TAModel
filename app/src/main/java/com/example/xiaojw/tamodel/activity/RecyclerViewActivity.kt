package com.example.xiaojw.tamodel.activity

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.common.baseframe.util.APPLOG
import com.example.xiaojw.tamodel.R

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        mRecyclerView = findViewById(R.id.test_rv);
        mRecyclerView.layoutManager = LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(CustomDecoration())
        mRecyclerView.adapter = DataAdapter(getDataList());

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
        dataList.add("DC")
        dataList.add("AB")
        dataList.add("FN")
        dataList.add("MZ")
        return dataList;
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
            val left = parent!!.paddingLeft+35
            for (i in 0..parent.childCount - 1) {
                val childView = parent.getChildAt(i)
                Log.d("Tag", "onDraw left____" + childView.left + ", right___" + childView.right + ", top___" + childView.top + ", bottom___" + childView.bottom)
                c?.drawLine(left.toFloat(), childView.top.toFloat()-20, left.toFloat(), childView.top + childView.height / 2 - radius, mPaint)
                c?.drawLine(left.toFloat(), childView.bottom - (childView.height / 2 - radius), left.toFloat(), childView.bottom.toFloat()+10, mPaint)
                mCirclePaint.color = Color.GREEN;
                c?.drawCircle(left.toFloat(), (childView.top + childView.height / 2).toFloat(), radius, mCirclePaint);
                mCirclePaint.color = resources.getColor(android.R.color.darker_gray)
                mCirclePaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
                c?.drawCircle(left.toFloat(), (childView.top + childView.height / 2).toFloat(), radius - 10, mCirclePaint);
            }

        }

        override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
//            c?.drawCircle(0f,0f,10f,mPaint)
            for (i in 0..(parent?.childCount!! -1)){
                var childView=parent.getChildAt(i);
                var bmp=BitmapFactory.decodeResource(resources,R.mipmap.timg)
                var dst=Rect(childView.left,childView.top+25,childView.left+200,childView.bottom-25)
                c?.drawBitmap(bmp,null,dst,mCirclePaint)
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
