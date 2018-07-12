package com.common.baseframe.view.rv.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xiaojw on 2017/12/13.
 * ReyclerView/LinerLayoutManger
 */

public class LinearItemDecoration {

    public static class Space extends RecyclerView.ItemDecoration {
        private int mOrientation;
        private int mValue;

        public Space(int value) {
            this(LinearLayoutManager.VERTICAL, value);
        }

        public Space(int orientation, int value) {
            mOrientation = orientation;
            mValue = value;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mValue);
            } else {
                outRect.set(0, 0, mValue, 0);
            }
        }
    }

    public static class Divider extends RecyclerView.ItemDecoration {
        private int mOrientation;
        private int mValue;
        private Paint mPaint;

        public Divider(int value) {
            this(LinearLayoutManager.VERTICAL, Color.RED, value);
        }

        public Divider(int orientation, int value) {
            this(orientation, Color.RED, value);
        }

        public Divider(int orientation, int color, int value) {
            mOrientation = orientation;
            mValue = value;
            mPaint = new Paint();
            mPaint.setColor(color);
            mPaint.setStrokeWidth(mValue);
        }


        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        private void drawHorizontal(Canvas c, RecyclerView parent) {
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() + parent.getPaddingBottom();
            for (int i = 0; i < parent.getChildCount(); i++) {
                View itemView = parent.getChildAt(i);
                int rgihtMargin = ((RecyclerView.LayoutParams) itemView.getLayoutParams()).rightMargin;
                int left = itemView.getRight() + rgihtMargin;
                c.drawLine(left, top, left, bottom, mPaint);
            }
        }

        private void drawVertical(Canvas c, RecyclerView parent) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() + parent.getPaddingRight();
            for (int i = 0; i < parent.getChildCount(); i++) {
                View itemView = parent.getChildAt(i);
                int bottomMargin = ((RecyclerView.LayoutParams) itemView.getLayoutParams()).bottomMargin;
                int top = itemView.getBottom() + bottomMargin;
                c.drawLine(left, top, right, top, mPaint);
            }
        }

    }


}
