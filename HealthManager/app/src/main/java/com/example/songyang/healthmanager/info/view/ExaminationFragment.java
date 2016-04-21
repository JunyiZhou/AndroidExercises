package com.example.songyang.healthmanager.info.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.bean.ExaminationBean;
import com.example.songyang.healthmanager.bean.UserBean;

public class ExaminationFragment extends Fragment {
    private static final String EXAMINATION_DATA = "EXAMINATION_DATA";

    public ExaminationFragment() {
    }

    public static ExaminationFragment newInstance(ExaminationBean examination) {
        ExaminationFragment fragment = new ExaminationFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXAMINATION_DATA, examination);
        fragment.setArguments(args);
        return fragment;
    }

    private ExaminationBean examination;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            examination = getArguments().getParcelable(EXAMINATION_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_examination, container, false);
    }

}
