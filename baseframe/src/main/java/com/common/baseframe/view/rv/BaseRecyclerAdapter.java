package com.common.baseframe.view.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by xiaojw on 2017/6/19.
 * 基础RecyclerAdapter封装
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mItems;
    protected OnItemClickListener listener;

    public BaseRecyclerAdapter(List<T> items) {
        mItems = items;
    }

    public void updateItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener=listener;
    }



    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseRecyclerHolder) {
            if (mItems!=null&&mItems.size()>0){
               T item=mItems.get(position);
               if (item!=null){
                   holder.itemView.setTag(item);
                   if (listener!=null){
                       listener.onItemClick(holder.itemView, position);
                   }
                   ((BaseRecyclerHolder) holder).initViewHolder(item);
               }
            }
        }
    }

    public  interface OnItemClickListener{

        void onItemClick(View view, int positoin);

    }



}
