package com.android.example.androidbase.service;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ServiceInterface {
    @FormUrlEncoded
    @POST
    Call<ResponseBody> postPath(@Url String path, @FieldMap HashMap<String, String> params);

    @GET
    Call<ResponseBody> getPath(@Url String path, @QueryMap HashMap<String, String> params);
}
