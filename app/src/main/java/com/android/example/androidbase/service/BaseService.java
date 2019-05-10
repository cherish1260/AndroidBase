package com.android.example.androidbase.service;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {
    //    public static final String BASE_URL = "https://fy.iciba.com/";
    public static final String BASE_URL = "https://fanyi.youdao.com/";
    public static OkHttpClient okHttpClient;
    public static Retrofit retrofit;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .connectTimeout(20L, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .build();
        retrofit = (new Retrofit.Builder())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(okHttpClient)
                .build();
    }

    public interface ServiceListener<T extends BaseResponse> {
        public void onRequestComplete(T response);
    }

    /**
     * 站内GET请求
     */
    public <T extends BaseResponse> Call<ResponseBody> getPathRetrofit(String path, // url
                                                                       Map<String, Object> param, // 参数列表
                                                                       final Class<T> responseClz, // 反序列化对象类型
                                                                       final ServiceListener<T> listener) {
        ServiceInterface service = retrofit.create(ServiceInterface.class);
        Call<ResponseBody> call = service.getPath(path, BaseRequest.processParam(param, false));
        handleResponse(call, responseClz, listener);
        return call;
    }

    /**
     * 站内Post请求
     */
    public <T extends BaseResponse> Call<ResponseBody> postPathRetrofit(String path, // url
                                                                        Map<String, Object> param, // 参数列表
                                                                        final Class<T> responseClz, // 反序列化对象类型
                                                                        final ServiceListener<T> listener) {
        ServiceInterface service = retrofit.create(ServiceInterface.class);
        Call<ResponseBody> call = service.postPath(path, BaseRequest.processParam(param, false));
        handleResponse(call, responseClz, listener);
        return call;
    }

    public <T extends BaseResponse> T convertObject(String json, final Class<T> responseClz) {
        Gson gson = new Gson();
        T response = gson.fromJson(json, responseClz);
        if (response == null) {
            try {
                response = responseClz.newInstance();
                response.setRetcode(BaseResponse.ERR_CODE_JSON_PARSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private <T extends BaseResponse> void handleResponse(Call<ResponseBody> call, final Class<T> responseClz, final ServiceListener<T> listener) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (listener == null) {
                    return;
                }
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        T baseResponse = convertObject(result, responseClz);
                        listener.onRequestComplete(baseResponse);
                    } catch (IOException e) {
                        try {
                            T res = responseClz.newInstance();
                            res.setRetcode(BaseResponse.ERR_CODE_JSON_PARSE);
                            res.setRetdesc(BaseResponse.ERR_TEXT_NETWORK_ERROR + " (" + BaseResponse.ERR_CODE_JSON_PARSE + ")");
                            listener.onRequestComplete(res);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                } else {
                    try {
                        T res = responseClz.newInstance();
                        res.setRetcode(BaseResponse.ERR_CODE_JSON_PARSE);
                        res.setRetdesc(BaseResponse.ERR_TEXT_NETWORK_ERROR + " (" + BaseResponse.ERR_CODE_JSON_PARSE + ")");
                        listener.onRequestComplete(res);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (listener == null) {
                    return;
                }
                try {
                    T res = responseClz.newInstance();
                    int code = BaseResponse.ERR_CODE_NET;
                    if (t instanceof SocketTimeoutException) {
                        code = BaseResponse.ERR_CODE_TIMEOUT;
                    }
                    res.setRetcode(code);
                    res.setRetdesc(BaseResponse.ERR_TEXT_NETWORK_ERROR + " (" + code + ")");
                    listener.onRequestComplete(res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
