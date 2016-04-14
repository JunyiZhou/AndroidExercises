package com.example.songyang.healthmanager.rostrum.model;

import com.example.songyang.healthmanager.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class RostrumModel implements IRostrumModel {
    @Override
    public List<String> getRostrumData() {
        List<String> rostrumData = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            rostrumData.add("" + (char) i);
        }
        return rostrumData;
    }
}
