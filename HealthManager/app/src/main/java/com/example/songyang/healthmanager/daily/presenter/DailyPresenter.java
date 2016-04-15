package com.example.songyang.healthmanager.daily.presenter;

import com.example.songyang.healthmanager.bean.StepBean;
import com.example.songyang.healthmanager.daily.model.DailyModel;
import com.example.songyang.healthmanager.daily.model.IDailyModel;
import com.example.songyang.healthmanager.daily.view.IDailyView;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class DailyPresenter implements IDailyPresenter {
    private IDailyModel mDailyModel;
    private IDailyView mDailyView;

    public DailyPresenter(IDailyView view) {
        mDailyView = view;
        mDailyModel = new DailyModel();
    }

    @Override
    public void loadLineDiagramData() {
        List<StepBean> steps = mDailyModel.getSteps();
        int[] yParams = {4000, 6000, 8000, 10000};
        float[] values = {4500, 6900, 9000};
        String[] xParams = {"2016-4-12", "2016-4-13", "2016-4-14"};

        mDailyView.setLineDiagramData(yParams, values, xParams, "步");
    }
}
