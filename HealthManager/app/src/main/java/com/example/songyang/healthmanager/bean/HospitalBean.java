package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/14.
 */
@StorIOSQLiteType(table = "hospital")
public class HospitalBean {
    @StorIOSQLiteColumn(name = "hospitalid", key = true)
    int hospitalid;

    @StorIOSQLiteColumn(name = "userid")
    int userid;

    @StorIOSQLiteColumn(name = "htime")
    long htime;

    @StorIOSQLiteColumn(name = "hdianose")
    String hdianose;

    @StorIOSQLiteColumn(name = "hday")
    int hday;

    @StorIOSQLiteColumn(name = "hresult")
    String hresult;

    public int getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(int hospitalid) {
        this.hospitalid = hospitalid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public long getHtime() {
        return htime;
    }

    public void setHtime(long htime) {
        this.htime = htime;
    }

    public String getHdianose() {
        return hdianose;
    }

    public void setHdianose(String hdianose) {
        this.hdianose = hdianose;
    }

    public int getHday() {
        return hday;
    }

    public void setHday(int hday) {
        this.hday = hday;
    }

    public String getHresult() {
        return hresult;
    }

    public void setHresult(String hresult) {
        this.hresult = hresult;
    }
}
