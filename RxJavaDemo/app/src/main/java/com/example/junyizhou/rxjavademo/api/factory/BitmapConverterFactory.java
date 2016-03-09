package com.example.junyizhou.rxjavademo.api.factory;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by JunyiZhou on 2016/3/3.
 */
public class BitmapConverterFactory extends Converter.Factory {
    public static BitmapConverterFactory create() {
        return new BitmapConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new BitmapResponseBodyConverter();
    }

}
