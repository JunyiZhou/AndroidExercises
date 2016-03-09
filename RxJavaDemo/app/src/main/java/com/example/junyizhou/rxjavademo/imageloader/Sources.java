package com.example.junyizhou.rxjavademo.imageloader;

import android.content.Context;

import com.example.junyizhou.rxjavademo.model.BitmapData;

import rx.Observable;
import rx.Observable.Transformer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class Sources {
    Context mContext;
    MemoryCacheOvservable mMemoryCacheOvservable;
    DiskCacheObservable mDiskCacheObservable;
    NetCacheObservable mNetCacheObservable;

    public Sources(Context mContext) {
        this.mContext = mContext;
        mMemoryCacheOvservable = new MemoryCacheOvservable();
        mDiskCacheObservable = new DiskCacheObservable(mContext);
        mNetCacheObservable = new NetCacheObservable();
    }


    public Observable<BitmapData> memory(String url) {
        return mMemoryCacheOvservable.getObservable(url);
    }

    public Observable<BitmapData> disk(String url) {
        return mDiskCacheObservable.getObservable(url)
                .filter(new Func1<BitmapData, Boolean>() {
                    @Override
                    public Boolean call(BitmapData bitmapData) {
                        return bitmapData.bitmap != null;
                    }
                })
                .doOnNext(new Action1<BitmapData>() {
                    @Override
                    public void call(BitmapData bitmapData) {
                        mMemoryCacheOvservable.putData(bitmapData);
                    }
                });

    }

    public Observable<BitmapData> network(String url) {
        return mNetCacheObservable.getObservable(url)
                .doOnNext(new Action1<BitmapData>() {
                    @Override
                    public void call(BitmapData bitmapData) {
                        mMemoryCacheOvservable.putData(bitmapData);
                        mDiskCacheObservable.putData(bitmapData);
                    }
                });
    }

//    Transformer<BitmapData, BitmapData> logSource(final String source) {
//        return dataObservable.doOnNext(data -> {
//            if (data != null && data.bitmap != null) {
//                Logger.i(source + " has the data you are looking for!");
//            } else {
//                Logger.i(source + " not has the data!");
//            }
//        });
//    }
}
