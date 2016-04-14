package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/12.
 */
@StorIOSQLiteType(table = "record")
public class ClinicBean {
    @StorIOSQLiteColumn(name = "clinicid", key = true)
    int clinicid;

    @StorIOSQLiteColumn(name = "userid")
    int userid;

    @StorIOSQLiteColumn(name = "conclusion")
    String conclusion;

    @StorIOSQLiteColumn(name = "cdocter")
    String cdocter;

    @StorIOSQLiteColumn(name = "ctime")
    long ctime;

    @StorIOSQLiteColumn(name = "chistory")
    String chistory;

    @StorIOSQLiteColumn(name = "ctest")
    String ctest;

    @StorIOSQLiteColumn(name = "cresult")
    String cresult;

    @StorIOSQLiteColumn(name = "crecommend")
    String crecommend;

    @StorIOSQLiteColumn(name = "ccholesterin")
    float ccholesterin;

    @StorIOSQLiteColumn(name = "cgao")
    float cgao;

    @StorIOSQLiteColumn(name = "cdi")
    float cdi;

    @StorIOSQLiteColumn(name = "cserumo")
    float cserumo;

    @StorIOSQLiteColumn(name = "cserumm")
    float cserumm;

    @StorIOSQLiteColumn(name = "csugar")
    float csugar;

    public int getClinicid() {
        return clinicid;
    }

    public void setClinicid(int clinicid) {
        this.clinicid = clinicid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getCdocter() {
        return cdocter;
    }

    public void setCdocter(String cdocter) {
        this.cdocter = cdocter;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getChistory() {
        return chistory;
    }

    public void setChistory(String chistory) {
        this.chistory = chistory;
    }

    public String getCtest() {
        return ctest;
    }

    public void setCtest(String ctest) {
        this.ctest = ctest;
    }

    public String getCresult() {
        return cresult;
    }

    public void setCresult(String cresult) {
        this.cresult = cresult;
    }

    public String getCrecommend() {
        return crecommend;
    }

    public void setCrecommend(String crecommend) {
        this.crecommend = crecommend;
    }

    public float getCcholesterin() {
        return ccholesterin;
    }

    public void setCcholesterin(float ccholesterin) {
        this.ccholesterin = ccholesterin;
    }

    public float getCgao() {
        return cgao;
    }

    public void setCgao(float cgao) {
        this.cgao = cgao;
    }

    public float getCdi() {
        return cdi;
    }

    public void setCdi(float cdi) {
        this.cdi = cdi;
    }

    public float getCserumo() {
        return cserumo;
    }

    public void setCserumo(float cserumo) {
        this.cserumo = cserumo;
    }

    public float getCserumm() {
        return cserumm;
    }

    public void setCserumm(float cserumm) {
        this.cserumm = cserumm;
    }

    public float getCsugar() {
        return csugar;
    }

    public void setCsugar(float csugar) {
        this.csugar = csugar;
    }
}
