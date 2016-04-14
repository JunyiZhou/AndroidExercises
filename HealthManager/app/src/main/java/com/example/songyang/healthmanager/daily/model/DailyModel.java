package com.example.songyang.healthmanager.daily.model;

import com.example.songyang.healthmanager.bean.StepBean;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class DailyModel implements IDailyModel {
    private List<StepBean> steps;

    @Override
    public List<StepBean> getSteps() {
        return steps;
    }
}
