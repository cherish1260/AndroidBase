package com.android.example.androidbase.webview;

import android.webkit.JavascriptInterface;

public class AndroidToJs extends Object {
    @JavascriptInterface
    public void hello() {
        System.out.println("JS调用了Android的hello方法");
    }
}
