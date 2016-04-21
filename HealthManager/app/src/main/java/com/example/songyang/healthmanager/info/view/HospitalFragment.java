package com.example.songyang.healthmanager.info.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.bean.HospitalBean;
import com.example.songyang.healthmanager.bean.UserBean;

public class HospitalFragment extends Fragment {
    private static final String HOSPITAL_DATA = "HOSPITAL_DATA";

    public HospitalFragment() {
    }

    public static HospitalFragment newInstance(HospitalBean hospital) {
        HospitalFragment fragment = new HospitalFragment();
        Bundle args = new Bundle();
        args.putParcelable(HOSPITAL_DATA, hospital);
        fragment.setArguments(args);
        return fragment;
    }

    private HospitalBean hospital;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hospital = getArguments().getParcelable(HOSPITAL_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospital, container, false);
    }

}
