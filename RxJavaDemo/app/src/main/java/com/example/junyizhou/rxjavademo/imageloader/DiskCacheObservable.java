package com.example.junyizhou.rxjavademo.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.junyizhou.rxjavademo.model.BitmapData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class DiskCacheObservable implements CacheObservable {
    Context mContext;
    File mCacheFile;

    public DiskCacheObservable(Context mContext) {
        this.mContext = mContext;
        mCacheFile = mContext.getCacheDir();
    }

    @Override
    public Observable<BitmapData> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<BitmapData>() {
            @Override
            public void call(Subscriber<? super BitmapData> subscriber) {
                File f = getFile(url);
                BitmapData BitmapData = new BitmapData(f, url);
                subscriber.onNext(BitmapData);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private File getFile(String url) {
        url = url.replaceAll(File.separator, "-");
        return new File(mCacheFile, url);
    }

    /**
     * save pictures downloaded from net to disk
     * @param data data to be saved
     */
    public void putData(final BitmapData data) {
        Observable.create(new Observable.OnSubscribe<BitmapData>() {
            @Override
            public void call(Subscriber<? super BitmapData> subscriber) {
                File f = getFile(data.url);
                OutputStream out = null;
                try {
                    out = new FileOutputStream(f);
                    Bitmap.CompressFormat format;
                    if (data.url.endsWith("png") || data.url.endsWith("PNG")) {
                        format = Bitmap.CompressFormat.PNG;
                    } else {
                        format = Bitmap.CompressFormat.JPEG;
                    }
                    data.bitmap.compress(format, 100, out);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
}
