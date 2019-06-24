package com.android.example.androidbase.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.android.example.androidbase.utils.MD5Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;

import retrofit2.http.Url;

public class BitmapDispatcher extends Thread {

    // 阻塞队列
    private LinkedBlockingDeque<BitmapRequest> requestQueue;
    private Handler handler = new Handler(Looper.getMainLooper());

    public BitmapDispatcher(LinkedBlockingDeque<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                BitmapRequest br = requestQueue.take();

                // 设置占位图片
                showLoadingImage(br);

                // 加载图片
                Bitmap bitmap = findBitMap(br);

                // 把图片显示到imageview
                showImageView(br, bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void showLoadingImage(BitmapRequest br) {
        if (br.getResId() > 0 && br.getImageView() != null) {
            final int resId = br.getResId();
            final ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                }
            });

        }
    }

    private Bitmap findBitMap(BitmapRequest br) {
        Bitmap bitmap = null;
        bitmap = downloadImage(br.getUrl());
        return bitmap;
    }

    private Bitmap downloadImage(String url) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL imgUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) imgUrl.openConnection();
            is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    private void showImageView(final BitmapRequest br, final Bitmap bitmap) {
        if (bitmap != null && br.getImageView() != null
                    && br.getUrlMd5().equals(br.getImageView().getTag())) {
            final ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (br.getRequestListener() != null) {
                        RequestListener listener = br.getRequestListener();
                        listener.onSuccess(bitmap);
                    }
                }
            });

        }
    }


}


