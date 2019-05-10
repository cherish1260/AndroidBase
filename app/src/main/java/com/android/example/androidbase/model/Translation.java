package com.android.example.androidbase.model;
import com.android.example.androidbase.model.content;

import java.io.Serializable;

public class Translation implements Serializable {
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

    private int status;

    private content content;
}
