package com.android.example.androidbase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.Nullable;

public class MyView extends Button {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 0
                break;
            case MotionEvent.ACTION_CANCEL: // 3
                break;
            case MotionEvent.ACTION_UP: // 1
                break;
            case MotionEvent.ACTION_MOVE: // 2
                break;
        }
        System.out.println(getId() + "======" + MotionEvent.ACTION_DOWN);
        return super.onTouchEvent(event);
    }
}
