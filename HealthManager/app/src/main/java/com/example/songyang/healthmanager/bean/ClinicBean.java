package com.example.songyang.healthmanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by SongYang on 2016/4/12.
 */
@StorIOSQLiteType(table = "record")
public class ClinicBean implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.clinicid);
        dest.writeInt(this.userid);
        dest.writeString(this.conclusion);
        dest.writeString(this.cdocter);
        dest.writeLong(this.ctime);
        dest.writeString(this.chistory);
        dest.writeString(this.ctest);
        dest.writeString(this.cresult);
        dest.writeString(this.crecommend);
        dest.writeFloat(this.ccholesterin);
        dest.writeFloat(this.cgao);
        dest.writeFloat(this.cdi);
        dest.writeFloat(this.cserumo);
        dest.writeFloat(this.cserumm);
        dest.writeFloat(this.csugar);
    }

    public ClinicBean() {
    }

    protected ClinicBean(Parcel in) {
        this.clinicid = in.readInt();
        this.userid = in.readInt();
        this.conclusion = in.readString();
        this.cdocter = in.readString();
        this.ctime = in.readLong();
        this.chistory = in.readString();
        this.ctest = in.readString();
        this.cresult = in.readString();
        this.crecommend = in.readString();
        this.ccholesterin = in.readFloat();
        this.cgao = in.readFloat();
        this.cdi = in.readFloat();
        this.cserumo = in.readFloat();
        this.cserumm = in.readFloat();
        this.csugar = in.readFloat();
    }

    public static final Creator<ClinicBean> CREATOR = new Creator<ClinicBean>() {
        public ClinicBean createFromParcel(Parcel source) {
            return new ClinicBean(source);
        }

        public ClinicBean[] newArray(int size) {
            return new ClinicBean[size];
        }
    };
}
