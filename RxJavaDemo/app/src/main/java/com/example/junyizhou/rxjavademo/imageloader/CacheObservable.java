package com.example.junyizhou.rxjavademo.imageloader;

import com.example.junyizhou.rxjavademo.model.BitmapData;

import rx.Observable;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public interface CacheObservable {
    Observable<BitmapData> getObservable(String url);
}
