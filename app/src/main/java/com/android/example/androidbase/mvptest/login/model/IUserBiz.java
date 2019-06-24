package com.android.example.androidbase.mvptest.login.model;

public interface IUserBiz {
    void login(String userName, String password, OnLoginListener listener);
}
