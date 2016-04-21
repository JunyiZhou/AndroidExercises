package com.example.songyang.healthmanager.info.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.bean.UserBean;

public class ProfileFragment extends Fragment {
    private static final String PROFILE_DATA = "PROFILE_DATA";

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(UserBean proflie) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(PROFILE_DATA, proflie);
        fragment.setArguments(args);
        return fragment;
    }

    private UserBean proflie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            proflie = getArguments().getParcelable(PROFILE_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
