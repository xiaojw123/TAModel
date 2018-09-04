package com.example.xiaojw.tamodel.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xiaojw.tamodel.R
import com.example.xiaojw.tamodel.util.CommonUtil

class NestedActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested)
        val items = CommonUtil.getDataItems()
        val vp: ViewPager = findViewById(R.id.nested_vp)
        val recylcerView = RecyclerView(this);
        val recyclerView2=RecyclerView(this);
        recylcerView.layoutManager = LinearLayoutManager(this)
        recyclerView2.layoutManager=LinearLayoutManager(this)
        val mAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
                val text = TextView(parent?.context)
                text.setTextColor(Color.BLUE)
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 33f);
                text.setPadding(0, 100, 0, 100)
                text.gravity = Gravity.CENTER
                return object : RecyclerView.ViewHolder(text) {

                }

            }

            override fun getItemCount(): Int {
                return items.size
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                val itemView = holder?.itemView
                if (itemView is TextView) {
                    itemView.setText(items.get(position))
                }

            }


        }
        recylcerView.adapter = mAdapter
        recyclerView2.adapter=mAdapter
        recyclerView2.visibility=View.GONE
        val viewList= listOf<View>(recylcerView,recyclerView2)
        vp.adapter=object :PagerAdapter(){
            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return true
            }

            override fun getCount(): Int {

                return viewList.size
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                container?.addView(viewList.get(position))
                return  viewList.get(position)
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container?.removeView(viewList.get(position))
            }



        }
        vp.setCurrentItem(0)


    }
}
