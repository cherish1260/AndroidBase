package com.android.example.androidbase.mvptest.login.presenter;

import android.os.Handler;

import com.android.example.androidbase.mvptest.login.model.IUserBiz;
import com.android.example.androidbase.mvptest.login.model.OnLoginListener;
import com.android.example.androidbase.mvptest.login.model.User;
import com.android.example.androidbase.mvptest.login.model.UserBiz;
import com.android.example.androidbase.mvptest.login.view.ILoginView;

public class UserPresener {

    private ILoginView iLoginView;
    private IUserBiz iUserBiz;
    private Handler mHandler = new Handler();

    public UserPresener(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        this.iUserBiz = new UserBiz();
    }

    public void login() {
        iLoginView.showLoading();
        iUserBiz.login(iLoginView.getUserName(), iLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void onSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.hideLoading();
                        iLoginView.toMainActivity(user);
                    }
                });
            }

            @Override
            public void onFail() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.hideLoading();
                        iLoginView.showFailedError();
                    }
                });

            }
        });
    }

    public void clear() {
        iLoginView.clearUserName();
        iLoginView.clearPassword();
    }
}
