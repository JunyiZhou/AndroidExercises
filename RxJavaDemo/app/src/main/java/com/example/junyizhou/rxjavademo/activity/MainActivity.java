package com.example.junyizhou.rxjavademo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.junyizhou.rxjavademo.R;
import com.example.junyizhou.rxjavademo.api.ApiManager;
import com.example.junyizhou.rxjavademo.databinding.ContentWeatherBinding;
import com.example.junyizhou.rxjavademo.imageloader.RxImageLoader;
import com.example.junyizhou.rxjavademo.model.User;
import com.example.junyizhou.rxjavademo.model.WeatherData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayoutInflater layoutInflater = getLayoutInflater();
        final ContentWeatherBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.content_weather,
                (FrameLayout) findViewById(R.id.content_root), true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ApiManager.apiManager.getWeather("北京")
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<WeatherData>() {
//                            @Override
//                            public void onCompleted() {
//                                System.out.println("done");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                System.out.println(e);
//                            }
//
//                            @Override
//                            public void onNext(WeatherData weatherData) {
//                                System.out.println(weatherData);
//                                binding.setWeather(weatherData);
//                                RxImageLoader.getLoaderObservable(binding.ivWeatherBannerStatusIcon, weatherData.getData().getImgurl()).subscribe();
//                            }
//                        });

                ApiManager.apiManager.getUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<User>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("done");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println(e);
                            }

                            @Override
                            public void onNext(User user) {
                                System.out.println(user);
                                Toast.makeText(MainActivity.this, "name:" + user.getName() + ",age:" + user.getAge(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public static byte[] getBytesFromStream(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        buf = new byte[size];
        while ((len = is.read(buf, 0, size)) != -1) {
            bos.write(buf, 0, len);
        }
        buf = bos.toByteArray();

        return buf;
    }

    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                }
            }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            System.out.println(s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
