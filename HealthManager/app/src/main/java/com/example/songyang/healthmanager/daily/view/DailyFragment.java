package com.example.songyang.healthmanager.daily.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.LineDiagramView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class DailyFragment extends Fragment {

    private LineDiagramView mLineDiagramView;

    private int[] yParams = {40, 60, 80, 100};
    private float[] values = {40.1f, 60.4f, 80.3f};
    private String[] xParams = {"4月", "5月", "6月"};

    public DailyFragment() {
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
        mLineDiagramView.setParams(yParams, values, xParams, "万");
        mLineDiagramView.show();
    }
}
