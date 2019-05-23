package com.android.modulea.service;

import com.android.basemodule.IAccountService;
import com.android.modulea.AccountUtils;

public class AccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return AccountUtils.userInfo != null;
    }

    @Override
    public String getUserId() {
        return AccountUtils.userInfo.getAccountId();
    }
}
