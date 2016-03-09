package com.example.junyizhou.rxjavademo.api;

import android.graphics.Bitmap;

import com.example.junyizhou.rxjavademo.model.WeatherData;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Url;
import rx.Observable;

/**
 * Created by JunyiZhou on 2016/3/2.
 */
public interface ApiManagerService {
    @GET("api/s/getWeatherInfo")
    Observable<WeatherData> getWeather(@Query("city") String city);

    @GET("ad/{id}.png")
    Observable<Bitmap> getBitmap(@Path("id") int id);

    @GET
    Observable<Bitmap> getBitmap(@Url String imageUrl);
}
