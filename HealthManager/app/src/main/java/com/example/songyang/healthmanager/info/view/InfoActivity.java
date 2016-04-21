package com.example.songyang.healthmanager.info.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.info.presenter.IInfoPresenter;
import com.example.songyang.healthmanager.info.presenter.InfoPresenter;

public class InfoActivity extends AppCompatActivity implements IInfoView {
    public static final String INFO_TYPE = "INFO_TYPE";
    public static final String INFO_PROFILE = "INFO_PROFILE";
    public static final String INFO_CLINIC = "INFO_CLINIC";
    public static final String INFO_HOSPITAL = "INFO_HOSPITAL";
    public static final String INFO_EXAMINATION = "INFO_EXAMINATION";

    public static void startInfoActivity(Context context, String type) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(INFO_TYPE, type);
        context.startActivity(intent);
    }

    private Toolbar toolbar;
    private String type;
    private IInfoPresenter mInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initData();
    }

    public void initToorbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData() {
        mInfoPresenter = new InfoPresenter(this);

        if (getIntent() == null) {
            return;
        }

        type = getIntent().getStringExtra(INFO_TYPE);

        //TODO userID
        mInfoPresenter.loadInfo(0, type);
    }

    @Override
    public void setFragment(Fragment fragment, String title) {
        initToorbar(title);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
