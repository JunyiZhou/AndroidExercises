package com.example.songyang.healthmanager.hospital.presenter;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.hospital.model.HospitalModel;
import com.example.songyang.healthmanager.hospital.model.IHospitalModel;
import com.example.songyang.healthmanager.hospital.view.IHospitalView;
import com.example.songyang.healthmanager.main.model.IUserModel;
import com.example.songyang.healthmanager.main.model.UserModel;
import com.example.songyang.healthmanager.main.view.IMainView;

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
}
