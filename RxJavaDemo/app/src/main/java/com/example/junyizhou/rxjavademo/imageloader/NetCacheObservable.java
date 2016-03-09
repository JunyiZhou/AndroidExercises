package com.example.junyizhou.rxjavademo.imageloader;

import android.graphics.Bitmap;

import com.example.junyizhou.rxjavademo.api.BitmapManager;
import com.example.junyizhou.rxjavademo.model.BitmapData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class NetCacheObservable implements CacheObservable {
    @Override
    public Observable<BitmapData> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<BitmapData>() {
            @Override
            public void call(final Subscriber<? super BitmapData> subscriber) {
//                "http://img01.cheyipai.com/ad/502.png"
                BitmapManager.apiManager.getBitmap(url)
                        .subscribe(new Subscriber<Bitmap>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("done");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println(e);
                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                BitmapData bitmapData = new BitmapData(bitmap, url);
                                if(!subscriber.isUnsubscribed()) {
                                    subscriber.onNext(bitmapData);
                                    subscriber.onCompleted();
                                }
                            }
                        });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
