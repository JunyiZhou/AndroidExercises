package com.example.junyizhou.rxjavademo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.example.junyizhou.rxjavademo.model.BitmapData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class RxImageLoader {
    static Sources sources;

    public static void init(Context mContext) {
        sources = new Sources(mContext);
    }

    private static final Map<Integer, String> cacheKeysMap = Collections.synchronizedMap(new HashMap<Integer, String>());

    /**
     * get the observable that load img and set it to the given ImageView
     *
     * @param img the ImageView to show this img
     * @param url the url for the img
     * @return the observable to load img
     */
    public static Observable<BitmapData> getLoaderObservable(final ImageView img, final String url) {
        if (img != null) {
            cacheKeysMap.put(img.hashCode(), url);
        }
        // Create our sequence for querying best available data
        Observable<BitmapData> source = Observable.concat(sources.memory(url), sources.disk(url), sources.network(url))
                .first(new Func1<BitmapData, Boolean>() {
                    @Override
                    public Boolean call(BitmapData bitmapData) {
                        return bitmapData != null && bitmapData.isAvailable() && url.equals(bitmapData.url);
                    }
                });

        return source.doOnNext(new Action1<BitmapData>() {
            @Override
            public void call(BitmapData bitmapData) {
                if (img != null && url.equals(cacheKeysMap.get(img.hashCode()))) {
                    img.setImageBitmap(bitmapData.bitmap);
                }
            }
        });
    }
}
