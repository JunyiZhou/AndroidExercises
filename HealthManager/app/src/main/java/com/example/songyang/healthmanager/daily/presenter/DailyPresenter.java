package com.example.songyang.healthmanager.daily.presenter;

import com.example.songyang.healthmanager.bean.StepBean;
import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.daily.model.DailyModel;
import com.example.songyang.healthmanager.daily.model.IDailyModel;
import com.example.songyang.healthmanager.daily.view.IDailyView;
import com.example.songyang.healthmanager.main.model.IUserModel;
import com.example.songyang.healthmanager.main.model.UserModel;
import com.example.songyang.healthmanager.main.view.IMainView;

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
        int[] yParams = {40, 60, 80, 100};
        float[] values = {40.1f, 60.4f, 80.3f};
        String[] xParams = {"4月", "5月", "6月"};

        mDailyView.setLineDiagramData(yParams, values, xParams, "万");
    }
}
