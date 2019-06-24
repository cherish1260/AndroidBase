package com.android.example.androidbase.mvptest.login.model;

import com.android.example.androidbase.mvptest.login.model.IUserBiz;
import com.android.example.androidbase.mvptest.login.model.OnLoginListener;
import com.android.example.androidbase.mvptest.login.model.User;

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String userName, final String password, final OnLoginListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ("zby".equals(userName) && "123456".equals(password)) {
                    User user = new User();
                    user.setPassword(password);
                    user.setUserName(userName);
                    listener.onSuccess(user);
                } else {
                    listener.onFail();
                }
            }
        }.start();
    }
}
