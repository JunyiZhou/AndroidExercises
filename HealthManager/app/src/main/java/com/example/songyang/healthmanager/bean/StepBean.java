package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/14.
 */
@StorIOSQLiteType(table = "step")
public class StepBean {
    @StorIOSQLiteColumn(name = "stepid", key = true)
    int stepid;

    @StorIOSQLiteColumn(name = "count")
    int count;

    @StorIOSQLiteColumn(name = "date")
    String date;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStepid() {
        return stepid;
    }

    public void setStepid(int stepid) {
        this.stepid = stepid;
    }
}
