package com.example.songyang.healthmanager.treat.model;

import android.net.Uri;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class TreatModel implements ITreatModel {
    @Override
    public Uri getImageUri() {
        Uri imageUri = Uri.parse("http://pic.baike.soso.com/p/20131112/20131112171121-299012034.jpg");
        return imageUri;
    }
}
