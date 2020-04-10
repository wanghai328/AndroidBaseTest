package com.test.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthSpecMode != MeasureSpec.EXACTLY) {
            try {
                throw new Exception("FlowLayout宽度值要固定");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();

        int height = paddingTop + paddingBottom;
        //子View的个数
        int childCount = getChildCount();
        //行宽(用来判断是否换行)
        int lineWidth = 0;
        int lineHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = 0;
            int childHeight = 0;

            childWidth = childView.getMeasuredWidth() + (lp == null ? 0 : lp.leftMargin + lp.rightMargin);
            childHeight = childView.getMeasuredHeight() + (lp == null ? 0 : lp.topMargin + lp.bottomMargin);

            lineHeight = Math.max(childHeight, lineHeight);
            lineWidth += childWidth;

            if (paddingLeft + paddingRight + lineWidth + childWidth > widthSpecSize) {
                height += lineHeight;
                lineWidth = 0;
                lineHeight = 0;
            }

        }
        setMeasuredDimension(
                widthSpecSize,
                heightSpecMode == MeasureSpec.EXACTLY ? heightSpecSize : height
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();


        int maxWidth = getMeasuredWidth();
        int left = paddingLeft;
        int top = paddingTop;

        int lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == GONE) {
                continue;
            }
            int cHeight = childView.getMeasuredHeight();
            int cWidth = childView.getMeasuredWidth();

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            int leftMargin = params.leftMargin;
            int topMargin = params.topMargin;
            int bottomMargin = params.bottomMargin;
            int rightMargin = params.rightMargin;

            lineHeight = Math.max(lineHeight, cHeight + topMargin + bottomMargin);

            //需要换行
            if (paddingLeft + paddingRight + left + cWidth + leftMargin + rightMargin > maxWidth) {
                left = paddingLeft;
                top += lineHeight;
                lineHeight = 0;
            }
            int childLeft = left + leftMargin;
            int childRight = childLeft + cWidth;
            int childTop = top + topMargin;
            int childBottom = childTop + childView.getMeasuredHeight();

            childView.layout(childLeft, childTop, childRight, childBottom);
            left += cWidth + leftMargin + rightMargin;
        }
    }


    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
