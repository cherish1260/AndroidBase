package com.android.example.androidbase.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.android.example.androidbase.R;

import androidx.annotation.Nullable;

public class TestViewEventActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("========ac" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
