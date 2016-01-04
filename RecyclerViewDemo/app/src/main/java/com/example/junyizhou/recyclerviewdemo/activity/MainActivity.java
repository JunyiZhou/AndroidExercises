package com.example.junyizhou.recyclerviewdemo.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.example.junyizhou.recyclerviewdemo.R;
import com.example.junyizhou.recyclerviewdemo.fragment.GridListDemoFragment;
import com.example.junyizhou.recyclerviewdemo.fragment.LinearListDemoFragment;
import com.example.junyizhou.recyclerviewdemo.fragment.StaggeredGridHorizontalListDemoFragment;
import com.example.junyizhou.recyclerviewdemo.fragment.StaggeredGridVerticalFlowListDemoFragment;
import com.example.junyizhou.recyclerviewdemo.fragment.StaggeredGridVerticalListDemoFragment;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
//        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
//        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    public void viewInit() {
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_staggered_grid_vertical).setOnClickListener(this);
        findViewById(R.id.btn_staggered_grid_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_staggered_grid_vertical_flow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.btn_linear:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new LinearListDemoFragment()).addToBackStack(null).commit();
                break;
            case R.id.btn_grid:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new GridListDemoFragment()).addToBackStack(null).commit();
                break;
            case R.id.btn_staggered_grid_vertical:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StaggeredGridVerticalListDemoFragment()).addToBackStack(null).commit();
                break;
            case R.id.btn_staggered_grid_horizontal:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StaggeredGridHorizontalListDemoFragment()).addToBackStack(null).commit();
                break;
            case R.id.btn_staggered_grid_vertical_flow:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StaggeredGridVerticalFlowListDemoFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
