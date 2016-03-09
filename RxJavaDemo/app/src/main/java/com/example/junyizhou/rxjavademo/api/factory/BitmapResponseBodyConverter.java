package com.example.junyizhou.rxjavademo.api.factory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by JunyiZhou on 2016/3/3.
 */
public class BitmapResponseBodyConverter implements Converter<ResponseBody, Bitmap> {

    @Override
    public Bitmap convert(ResponseBody value) throws IOException {
        return BitmapFactory.decodeStream(value.byteStream());
    }
}
