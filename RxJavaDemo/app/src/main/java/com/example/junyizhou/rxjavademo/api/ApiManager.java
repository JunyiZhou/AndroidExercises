package com.example.junyizhou.rxjavademo.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by JunyiZhou on 2016/3/2.
 */
public class ApiManager {
    private static final String BASE_URL = "http://192.168.51.43:8080/TomcatTest/";
    private static final Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    public static final ApiManagerService apiManager = retrofit.create(ApiManagerService.class);

    public static ApiManagerService getInstance() {
        return apiManager;
    }
}
