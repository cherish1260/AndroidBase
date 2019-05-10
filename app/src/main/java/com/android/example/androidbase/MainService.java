package com.android.example.androidbase;

import com.android.example.androidbase.params.GetParam;
import com.android.example.androidbase.params.PostParam;
import com.android.example.androidbase.response.TranslationPostResponse;
import com.android.example.androidbase.response.TranslationResponse;
import com.android.example.androidbase.service.BaseService;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainService extends BaseService {

    private static MainService instance = new MainService();

    private MainService() {

    }

    public static MainService getInstance() {
        return instance;
    }

    public Call<ResponseBody> getTranslation(GetParam params, ServiceListener<TranslationResponse> listener) {
        HashMap<String,Object> param_ = new HashMap<>();
        param_.putAll(params.toMap());
        return this.getPathRetrofit("ajax.php", param_, TranslationResponse.class, listener);
    }

    public Call<ResponseBody> postTranslation(PostParam params, ServiceListener<TranslationPostResponse> listener) {
        HashMap<String, Object> param_ = new HashMap<>();
        param_.putAll(params.toMap());
        return this.postPathRetrofit("translate", param_, TranslationPostResponse.class, listener);
    }
}
