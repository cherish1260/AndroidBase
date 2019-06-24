package com.android.example.androidbase.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toMD5(String key) {
        if (digest == null) {
            return String.valueOf(key.hashCode());
        }
        // 更新字节
        digest.update(key.getBytes());
        // 获取最终的摘要
        return converter2HexString(digest.digest());
    }

    public static String converter2HexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(byte b :bytes) {
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
