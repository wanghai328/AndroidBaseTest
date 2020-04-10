package com.test.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    private int childCount = 0;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;

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
        childCount = getChildCount();

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();

        int height = paddingTop + paddingBottom;
        //子View的个数

        //行宽(用来判断是否换行)
        int lineWidth = 0;
        int lineHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            lineHeight = Math.max(childHeight, lineHeight);
            lineWidth += childWidth;

            if (paddingLeft + paddingRight + lineWidth  > widthSpecSize) {
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

        int maxWidth = getMeasuredWidth();
        int left = paddingLeft;
        int top = paddingTop;

        int lineHeight = 0;
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
