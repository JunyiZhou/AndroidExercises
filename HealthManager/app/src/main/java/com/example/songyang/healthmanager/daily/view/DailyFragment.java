package com.example.songyang.healthmanager.daily.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.LineDiagramView;
import com.example.songyang.healthmanager.daily.presenter.DailyPresenter;
import com.example.songyang.healthmanager.daily.presenter.IDailyPresenter;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class DailyFragment extends Fragment implements IDailyView {

    private LineDiagramView mLineDiagramView;

    private IDailyPresenter mDailyPresenter;

    public DailyFragment() {
        mDailyPresenter = new DailyPresenter(this);
    }

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLineDiagramView = (LineDiagramView) view.findViewById(R.id.line_diagram_view);
        mDailyPresenter.loadLineDiagramData();
    }

    @Override
    public void setLineDiagramData(int[] yParams, float[] values, String[] xParams, String unite) {
        mLineDiagramView.setParams(yParams, values, xParams, unite);
        mLineDiagramView.show();
    }
}
