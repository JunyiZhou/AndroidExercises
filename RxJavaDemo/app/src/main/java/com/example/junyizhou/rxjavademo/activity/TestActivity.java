package com.example.junyizhou.rxjavademo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.junyizhou.rxjavademo.R;
import com.example.junyizhou.rxjavademo.TestView;

public class TestActivity extends AppCompatActivity {

    private TestView testView;
    private ImageView testImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        testView = (TestView) findViewById(R.id.test_view);
        testView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        testImageView = (ImageView) findViewById(R.id.test_imageview);
        Glide.with(this).fromResource().load(R.drawable.icon_weather_location).into(testImageView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
