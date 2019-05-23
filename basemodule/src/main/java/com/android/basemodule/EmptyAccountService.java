package com.android.basemodule;

public class EmptyAccountService implements IAccountService {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getUserId() {
        return null;
    }
}
