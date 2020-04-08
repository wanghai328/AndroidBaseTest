package com.test.mytest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private Scroller scroller;
     public CustomView(Context context) {
        this(context,null);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);


        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
         int scrollX = getScrollX();
         int delta = destX-scrollX;
         scroller.startScroll(scrollX,0,delta,2000);
         invalidate();
    }
}
