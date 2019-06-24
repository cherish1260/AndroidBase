package com.android.example.androidbase.mvptest.login.model;

public interface OnLoginListener {

    void onSuccess(User user);

    void onFail();
}
