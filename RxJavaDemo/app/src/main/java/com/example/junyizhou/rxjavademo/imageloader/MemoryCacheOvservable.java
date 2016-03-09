package com.example.junyizhou.rxjavademo.imageloader;

import com.example.junyizhou.rxjavademo.model.BitmapData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class MemoryCacheOvservable implements CacheObservable {
    public static final int DEFAULT_CACHE_SIZE = (24 /* MiB */ * 1024 * 1024);
    MemoryCache<String> mCache = new MemoryCache<>(DEFAULT_CACHE_SIZE);

    @Override
    public Observable<BitmapData> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<BitmapData>() {
            @Override
            public void call(Subscriber<? super BitmapData> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(new BitmapData(mCache.get(url), url));
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void putData(BitmapData data) {
        mCache.put(data.url, data.bitmap);
    }
}
