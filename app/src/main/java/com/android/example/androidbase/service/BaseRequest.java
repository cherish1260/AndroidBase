package com.android.example.androidbase.service;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {

    public static HashMap<String, String> processParam(Map<String, Object> params, boolean isSecurity) {
        HashMap<String, String> retMap = new HashMap<>();

        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    if (isWrapClass(value.getClass()) || value instanceof String) {
                        retMap.put(entry.getKey(), value.toString());
                    } else {
                        Gson gson = new Gson();
                        retMap.put(entry.getKey(), gson.toJson(value));
                    }
                }
            }
        }
        return retMap;
    }

    /**
     * 返回对象是否为原始类型
     */
    private static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive() || clz.getClass().isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
