package com.android.example.androidbase.service;

public class BaseResponse {
    public static final int ERR_CODE_NET = -1;
    public static final int ERR_CODE_USER_CANCEL = -2;
    public static final int ERR_CODE_JSON_PARSE = -3;
    public static final int ERR_CODE_GEO_PARSE = -4;
    public static final int ERR_CODE_SERVER = -5;
    public static final int ERR_CODE_TIMEOUT = -6;
    public static final int ERR_CODE_PARSE = -7;
    public static final int ERR_CODE_AUTHFAILURE = -8;
    public static final int RET_CODE_SUCCESS = 200;
    public static final String ERR_TEXT_NETWORK_ERROR = "网络不给力，请稍后重试";
    public static final String ERR_TEXT_JSON_PARSE = "网络不给力，请稍后重试";
    protected String retdesc = "网络不给力，请稍后重试";
    private int retcode = -1;

    public BaseResponse() {
    }

    public int getRetcode() {
        return this.retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetdesc() {
        return this.retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public boolean isSuccess() {
        return this.retcode == 200;
    }
}
