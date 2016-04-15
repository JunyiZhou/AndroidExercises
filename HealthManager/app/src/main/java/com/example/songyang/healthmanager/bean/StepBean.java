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

    @StorIOSQLiteColumn(name = "time")
    long time;

    public int getStepid() {
        return stepid;
    }

    public void setStepid(int stepid) {
        this.stepid = stepid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getDate() {
        return time;
    }

    public void setDate(long date) {
        this.time = date;
    }
}
