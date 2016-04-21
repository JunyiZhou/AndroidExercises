package com.example.songyang.healthmanager.info.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.bean.ClinicBean;
import com.example.songyang.healthmanager.bean.UserBean;

public class ClinicFragment extends Fragment {
    private static final String CLINIC_DATA = "CLINIC_DATA";

    public ClinicFragment() {
    }

    public static ClinicFragment newInstance(ClinicBean clinic) {
        ClinicFragment fragment = new ClinicFragment();
        Bundle args = new Bundle();
        args.putParcelable(CLINIC_DATA, clinic);
        fragment.setArguments(args);
        return fragment;
    }

    private ClinicBean clinic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clinic = getArguments().getParcelable(CLINIC_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clinic, container, false);
    }

}
