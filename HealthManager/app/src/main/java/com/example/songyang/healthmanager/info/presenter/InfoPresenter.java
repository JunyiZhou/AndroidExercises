package com.example.songyang.healthmanager.info.presenter;

import com.example.songyang.healthmanager.bean.ClinicBean;
import com.example.songyang.healthmanager.bean.ExaminationBean;
import com.example.songyang.healthmanager.bean.HospitalBean;
import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.info.view.ClinicFragment;
import com.example.songyang.healthmanager.info.view.ExaminationFragment;
import com.example.songyang.healthmanager.info.view.HospitalFragment;
import com.example.songyang.healthmanager.info.view.IInfoView;
import com.example.songyang.healthmanager.info.view.InfoActivity;
import com.example.songyang.healthmanager.info.view.ProfileFragment;
import com.example.songyang.healthmanager.main.model.IMainModel;
import com.example.songyang.healthmanager.main.model.MainModel;

/**
 * Created by SongYang on 2016/4/13.
 */
public class InfoPresenter implements IInfoPresenter {
    private IMainModel mUserModel;
    private IInfoView mInfoView;

    public InfoPresenter(IInfoView view) {
        mInfoView = view;
        mUserModel = new MainModel();
    }

    //TODO 使用model获取真实数据
    @Override
    public void loadInfo(int userId, String infoType) {

        switch (infoType) {
            case InfoActivity.INFO_CLINIC:
                mInfoView.setFragment(ClinicFragment.newInstance(new ClinicBean()), "门诊数据");
                break;
            case InfoActivity.INFO_HOSPITAL:
                mInfoView.setFragment(HospitalFragment.newInstance(new HospitalBean()), "住院数据");
                break;
            case InfoActivity.INFO_EXAMINATION:
                mInfoView.setFragment(ExaminationFragment.newInstance(new ExaminationBean()), "体检数据");
                break;
            case InfoActivity.INFO_PROFILE:
                mInfoView.setFragment(ProfileFragment.newInstance(new UserBean()), "个人基本信息");
                break;
            default:
                break;
        }
    }
}
