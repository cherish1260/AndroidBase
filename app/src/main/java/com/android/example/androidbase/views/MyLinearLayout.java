package com.android.example.androidbase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println(getId() + "==========vg" + ev.getAction());
        return super.dispatchTouchEvent(ev);
//        if (getId() == getResources().getIdentifier("secondParent", null, null)) {
//            return super.dispatchTouchEvent(ev);
//        }
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_UP:
//                return false;
//            default:
//                return super.dispatchTouchEvent(ev);
//        }
    }
}
