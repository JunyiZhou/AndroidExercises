package com.example.junyizhou.rxjavademo;

import android.app.Application;

import com.example.junyizhou.rxjavademo.imageloader.RxImageLoader;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class RxJavaDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxImageLoader.init(this);
    }
}
