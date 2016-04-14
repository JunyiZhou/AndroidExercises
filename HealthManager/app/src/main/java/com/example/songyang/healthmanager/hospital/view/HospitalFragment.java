package com.example.songyang.healthmanager.hospital.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.hospital.presenter.HospitalPresenter;
import com.example.songyang.healthmanager.hospital.presenter.IHospitalPresenter;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class HospitalFragment extends Fragment implements IHospitalView {
    private IHospitalPresenter mHospitalPresenter;

    public HospitalFragment() {
        mHospitalPresenter = new HospitalPresenter(this);
    }

    public static HospitalFragment newInstance() {
        HospitalFragment fragment = new HospitalFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospital, container, false);
    }
}
