package com.android.modulea;

import android.app.Application;

import com.android.basemodule.BaseApp;
import com.android.basemodule.ServiceFactory;
import com.android.modulea.service.AccountService;

public class AApp extends BaseApp {
    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new AccountService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
