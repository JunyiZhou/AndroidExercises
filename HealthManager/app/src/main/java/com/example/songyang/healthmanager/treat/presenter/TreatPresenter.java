package com.example.songyang.healthmanager.treat.presenter;

import com.example.songyang.healthmanager.treat.model.TreatModel;
import com.example.songyang.healthmanager.treat.model.ITreatModel;
import com.example.songyang.healthmanager.treat.view.ITreatView;

import java.util.List;

/**
 * Created by SongYang on 2016/4/13.
 */
public class TreatPresenter implements ITreatPresenter {
    private ITreatModel mTreatModel;
    private ITreatView mTreatView;

    public TreatPresenter(ITreatView view) {
        mTreatView = view;
        mTreatModel = new TreatModel();
    }

    @Override
    public void load() {
        List<String> departments = mTreatModel.getDepartmentData();
        mTreatView.setDepartmentData(departments);
    }
}
