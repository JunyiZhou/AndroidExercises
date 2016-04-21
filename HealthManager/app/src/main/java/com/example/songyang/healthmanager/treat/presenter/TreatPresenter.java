package com.example.songyang.healthmanager.treat.presenter;

import android.net.Uri;

import com.example.songyang.healthmanager.treat.model.TreatModel;
import com.example.songyang.healthmanager.treat.model.ITreatModel;
import com.example.songyang.healthmanager.treat.view.ITreatView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class TreatPresenter implements ITreatPresenter {
    private ITreatModel mHospitalModel;
    private ITreatView mHospitalView;

    public TreatPresenter(ITreatView view) {
        mHospitalView = view;
        mHospitalModel = new TreatModel();
    }

    @Override
    public void load() {
        Uri imageUri = mHospitalModel.getImageUri();
        mHospitalView.setImage(imageUri);
    }
}
