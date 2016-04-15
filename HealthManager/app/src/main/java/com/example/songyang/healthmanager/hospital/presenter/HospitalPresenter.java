package com.example.songyang.healthmanager.hospital.presenter;

import android.net.Uri;

import com.example.songyang.healthmanager.hospital.model.HospitalModel;
import com.example.songyang.healthmanager.hospital.model.IHospitalModel;
import com.example.songyang.healthmanager.hospital.view.IHospitalView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class HospitalPresenter implements IHospitalPresenter {
    private IHospitalModel mHospitalModel;
    private IHospitalView mHospitalView;

    public HospitalPresenter(IHospitalView view) {
        mHospitalView = view;
        mHospitalModel = new HospitalModel();
    }

    @Override
    public void load() {
        Uri imageUri = mHospitalModel.getImageUri();
        mHospitalView.setImage(imageUri);
    }
}
