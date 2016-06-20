package com.example.songyang.healthmanager.treat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SongYang on 2016/4/13.
 */
public class TreatModel implements ITreatModel {

    @Override
    public List<String> getDepartmentData() {
        List<String> departmentData = new ArrayList<>();
        departmentData.add("");
        departmentData.add("儿科");
        departmentData.add("皮肤科");
        departmentData.add("内科");
        departmentData.add("中医科");
        departmentData.add("眼科");
        departmentData.add("口腔科");
        departmentData.add("骨伤科");
        departmentData.add("耳鼻喉科");
        departmentData.add("妇科");
        return departmentData;
    }
}
