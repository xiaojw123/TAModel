package com.common.baseframe.view.rv.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaojw on 2017/12/14.
 * TODO: 2017/12/14  Diver有bug
 *
 */

public class GridItemDecoration {
    public static class Space extends RecyclerView.ItemDecoration {
        int mValue;

        public Space(int value) {
            mValue = value;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildLayoutPosition(view);
            GridLayoutManager gm = (GridLayoutManager) parent.getLayoutManager();
            int colum = gm.getSpanCount();
            int left = mValue;
            int top = mValue;
            if (position % colum == 0) {
                left = 0;
            }
            if (position < colum) {
                top = 0;
            }
            outRect.set(left, top, 0, 0);
        }
    }

    public static class Divider extends RecyclerView.ItemDecoration {

        int mValue;
        Paint mPaint;

        public Divider(int value) {
            this(Color.BLUE, value);
        }

        public Divider(int color, int value) {
            mValue = value;
            mPaint = new Paint();
            mPaint.setColor(color);
        }


        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            drawHoritional(c, parent);
            drawVertical(c, parent);
        }

        private void drawVertical(Canvas c, RecyclerView parent) {
            for (int i=0;i<parent.getChildCount();i++){
                View itemView=parent.getChildAt(i);
                RecyclerView.LayoutParams lm= (RecyclerView.LayoutParams) itemView.getLayoutParams();
                int left=itemView.getRight()+lm.rightMargin;
                int right=left+mValue;
                int top=itemView.getTop()-lm.topMargin;
                int bottom=itemView.getBottom()+lm.bottomMargin;
                c.drawLine(left,top,right,bottom,mPaint);
            }

        }

        private void drawHoritional(Canvas c, RecyclerView parent) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View itemView = parent.getChildAt(i);
                RecyclerView.LayoutParams lm = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                int left = itemView.getLeft() - lm.leftMargin;
                int right = itemView.getRight() + lm.rightMargin;
                int top = itemView.getBottom() + lm.bottomMargin;
                int bottom = top + mValue;
                c.drawLine(left, top, right, bottom, mPaint);
            }


        }
    }


}
