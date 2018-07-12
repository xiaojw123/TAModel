package com.example.xiaojw.tamodel

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DataListAdapter:RecyclerView.Adapter<DataListAdapter.DataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        if (viewType==0){
            return DataHolder(mRefreshHeader)
        }else{

            var contentTv=TextView(parent.context);
            contentTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
            contentTv.setTextColor(Color.BLUE)
            contentTv.layoutParams=RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT)
            return DataHolder(contentTv)

        }

    }
    lateinit var mRefreshHeader:RefreshHeader


    fun  setRefreshHeader(header:RefreshHeader){
        mRefreshHeader=header
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return 50;
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
         if ( holder.itemView is TextView){
             holder.itemView.setText("TeVi.."+position)
         }

    }


    open class DataHolder(itemView: View):RecyclerView.ViewHolder(itemView){




    }
}