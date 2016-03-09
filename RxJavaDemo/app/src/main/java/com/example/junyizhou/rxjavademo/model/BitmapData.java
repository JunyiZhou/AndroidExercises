package com.example.junyizhou.rxjavademo.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by JunyiZhou on 2016/3/7.
 */
public class BitmapData {
    public Bitmap bitmap;
    public String url;
    private boolean isAvailable;

    public BitmapData(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }

    public BitmapData(File f, String url) {
        if (f != null && f.exists()) {
            this.url = url;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAvailable() {
        isAvailable = url != null && bitmap != null;
        return isAvailable;
    }
}
