package com.common.baseframe.view.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaojw on 2017/6/19.
 */

public abstract class BaseRecyclerHolder<T> extends RecyclerView.ViewHolder {
   protected View mItemView;
    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        mItemView=itemView;
    }

    public abstract  void initViewHolder(T item);
}
