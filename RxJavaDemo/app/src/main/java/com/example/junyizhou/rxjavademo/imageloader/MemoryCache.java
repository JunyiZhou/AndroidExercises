package com.example.junyizhou.rxjavademo.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class MemoryCache<T> extends LruCache<T, Bitmap>{
    public MemoryCache(int maxSize) {
        super(maxSize);
    }
}
