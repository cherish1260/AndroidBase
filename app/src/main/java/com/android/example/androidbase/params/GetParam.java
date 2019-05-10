package com.android.example.androidbase.params;

import java.util.HashMap;

public class GetParam {
    private String a;
    private String f;
    private String t;
    private String w;

    public GetParam() {
    }

    public GetParam(String a, String f, String t, String w) {
        this.a = a;
        this.f = f;
        this.t = t;
        this.w = w;
    }

    public HashMap<String, Object> toMap() {
        HashMap map = new HashMap();
        map.put("a", this.a);
        map.put("f", this.f);
        map.put("t", this.t);
        map.put("w", this.w);
        return map;
    }
}
