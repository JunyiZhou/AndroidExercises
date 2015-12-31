package com.example.lucifer.animationdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.lucifer.animationdemo.drawable.CirclePercentDrawable;
import com.example.lucifer.animationdemo.drawable.WatchDrawable;
import com.example.lucifer.animationdemo.view.LineView;


public class MainActivity extends Activity {

    private Button button;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineView lineView = (LineView) findViewById(R.id.lineview);
        lineView.setCoordinate(10f, 10f, 200f, 200f);
//        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(this);
//
//        fl = (FrameLayout) findViewById(R.id.fl);
//        fl.setBackground(new CirclePercentDrawable(this, 0.7f, "#f0f0f0", "#71d282", 10000));
    }

}
