package com.android.example.androidbase.response;

import com.android.example.androidbase.model.content;
import com.android.example.androidbase.service.BaseResponse;

public class TranslationResponse extends BaseResponse {

    private int status;
    private content content;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public com.android.example.androidbase.model.content getContent() {
        return content;
    }

    public void setContent(com.android.example.androidbase.model.content content) {
        this.content = content;
    }
}
