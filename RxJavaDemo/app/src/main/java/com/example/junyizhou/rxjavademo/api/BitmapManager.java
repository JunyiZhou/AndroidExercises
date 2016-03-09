package com.example.junyizhou.rxjavademo.api;

import com.example.junyizhou.rxjavademo.api.factory.BitmapConverterFactory;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by JunyiZhou on 2016/3/3.
 */
public class BitmapManager {
    private static final Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://img01.cheyipai.com/")
            .addConverterFactory(BitmapConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public static final ApiManagerService apiManager = retrofit.create(ApiManagerService.class);

    public static ApiManagerService getInstance() {
        return apiManager;
    }
}
