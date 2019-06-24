package com.android.example.androidbase.glide;

import java.util.concurrent.LinkedBlockingDeque;

public class RequestManager {

    private static RequestManager instance = new RequestManager();

    private LinkedBlockingDeque<BitmapRequest> requestQueue = new LinkedBlockingDeque<>();

    // 创建一个线程数组
    private BitmapDispatcher[] bitmapDispatchers;

    private RequestManager() {
        start();
    }

    // 统一管理线程
    public void start() {
        stop();
        startAllDispatcher();
    }

    public static RequestManager getInstance() {
        return instance;
    }

    // 将图片对象加入到队列里
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);
        }
    }

    // 创建并开始所有的线程
    public void startAllDispatcher() {
        // 获取手机支持的单个应用的最大线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for(int i = 0; i< threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatcher.start();
            // 要把所有的dispatcher放到数组中，方便统一管理
            bitmapDispatchers[i] = bitmapDispatcher;
        }
    }

    // 停止所有的线程
    public void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for(BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.interrupt();
                }
            }
        }
    }
}
