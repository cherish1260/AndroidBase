package com.android.example.androidbase;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;

import com.android.example.androidbase.model.Translation;
import com.android.example.androidbase.model.Translation2;
import com.android.example.androidbase.params.GetParam;
import com.android.example.androidbase.params.PostParam;
import com.android.example.androidbase.response.TranslationPostResponse;
import com.android.example.androidbase.response.TranslationResponse;
import com.android.example.androidbase.service.BaseService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void grequest() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
//                response.body().show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

    public void prequest() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation2> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation2>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation2> call, Response<Translation2> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation2> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

    public void getRequest(View view) {
        // grequest();
        GetParam params = new GetParam("fy", "auto", "auto", "hello world");
        MainService.getInstance().getTranslation(params, new BaseService.ServiceListener<TranslationResponse>() {
            @Override
            public void onRequestComplete(TranslationResponse response) {
                if(response.isSuccess()) {
                    System.out.println(response);
                }
            }
        });
    }

    public void postRequest(View view) {
        // prequest();
        // 以下是使用封装好的service进行请求
        PostParam postParam = new PostParam("json", "", "", "", "", "", "", "", "", "", "", "", "hello");
        MainService.getInstance().postTranslation(postParam, new BaseService.ServiceListener<TranslationPostResponse>() {
            @Override
            public void onRequestComplete(TranslationPostResponse response) {
                if(response.isSuccess()) {
                    System.out.println(response);
                }
            }
        });
    }
}
