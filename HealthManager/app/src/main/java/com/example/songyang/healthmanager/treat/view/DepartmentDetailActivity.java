package com.example.songyang.healthmanager.treat.view;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.message.MessageListFragment;

public class DepartmentDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        initToorbar(getIntent().getStringExtra("DEPARTMENT_NAME"));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DoctorListFragment()).commit();
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
}
