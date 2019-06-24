package com.android.example.androidbase.mvptest.login.view;

import com.android.example.androidbase.mvptest.login.model.User;

public interface ILoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
