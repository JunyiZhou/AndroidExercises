package com.example.songyang.healthmanager.hospital.model;

import android.net.Uri;

import com.example.songyang.healthmanager.bean.StepBean;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class HospitalModel implements IHospitalModel {
    @Override
    public Uri getImageUri() {
        Uri imageUri = Uri.parse("http://pic.baike.soso.com/p/20131112/20131112171121-299012034.jpg");
        return imageUri;
    }
}
