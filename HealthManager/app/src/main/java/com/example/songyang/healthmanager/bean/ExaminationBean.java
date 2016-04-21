package com.example.songyang.healthmanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/14.
 */
@StorIOSQLiteType(table = "examination")
public class ExaminationBean implements Parcelable {
    @StorIOSQLiteColumn(name = "examinationid", key = true)
    int examinationid;

    @StorIOSQLiteColumn(name = "userid")
    int userid;

    @StorIOSQLiteColumn(name = "etime")
    long etime;

    @StorIOSQLiteColumn(name = "etcho")
    float etcho;

    @StorIOSQLiteColumn(name = "epressure")
    String epressure;

    @StorIOSQLiteColumn(name = "ebmi")
    float ebmi;

    @StorIOSQLiteColumn(name = "edbp")
    int edbp;

    @StorIOSQLiteColumn(name = "esys")
    int esys;

    @StorIOSQLiteColumn(name = "eglu")
    float eglu;

    @StorIOSQLiteColumn(name = "eliverc")
    String eliverc;

    @StorIOSQLiteColumn(name = "ekidneyc")
    String ekidneyc;

    @StorIOSQLiteColumn(name = "eglanc")
    String eglanc;

    @StorIOSQLiteColumn(name = "emilkc")
    String emilkc;

    @StorIOSQLiteColumn(name = "echest")
    String echest;

    @StorIOSQLiteColumn(name = "egutc")
    String egutc;

    @StorIOSQLiteColumn(name = "eph")
    int eph;

    @StorIOSQLiteColumn(name = "epissglu")
    float epissglu;

    @StorIOSQLiteColumn(name = "eeyepre")
    String eeyepre;

    @StorIOSQLiteColumn(name = "epanc")
    String epanc;

    @StorIOSQLiteColumn(name = "etc")
    float etc;

    public int getExaminationid() {
        return examinationid;
    }

    public void setExaminationid(int examinationid) {
        this.examinationid = examinationid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public long getEtime() {
        return etime;
    }

    public void setEtime(long etime) {
        this.etime = etime;
    }

    public float getEtcho() {
        return etcho;
    }

    public void setEtcho(float etcho) {
        this.etcho = etcho;
    }

    public String getEpressure() {
        return epressure;
    }

    public void setEpressure(String epressure) {
        this.epressure = epressure;
    }

    public float getEbmi() {
        return ebmi;
    }

    public void setEbmi(float ebmi) {
        this.ebmi = ebmi;
    }

    public int getEdbp() {
        return edbp;
    }

    public void setEdbp(int edbp) {
        this.edbp = edbp;
    }

    public int getEsys() {
        return esys;
    }

    public void setEsys(int esys) {
        this.esys = esys;
    }

    public float getEglu() {
        return eglu;
    }

    public void setEglu(float eglu) {
        this.eglu = eglu;
    }

    public String getEliverc() {
        return eliverc;
    }

    public void setEliverc(String eliverc) {
        this.eliverc = eliverc;
    }

    public String getEkidneyc() {
        return ekidneyc;
    }

    public void setEkidneyc(String ekidneyc) {
        this.ekidneyc = ekidneyc;
    }

    public String getEglanc() {
        return eglanc;
    }

    public void setEglanc(String eglanc) {
        this.eglanc = eglanc;
    }

    public String getEmilkc() {
        return emilkc;
    }

    public void setEmilkc(String emilkc) {
        this.emilkc = emilkc;
    }

    public String getEchest() {
        return echest;
    }

    public void setEchest(String echest) {
        this.echest = echest;
    }

    public String getEgutc() {
        return egutc;
    }

    public void setEgutc(String egutc) {
        this.egutc = egutc;
    }

    public int getEph() {
        return eph;
    }

    public void setEph(int eph) {
        this.eph = eph;
    }

    public float getEpissglu() {
        return epissglu;
    }

    public void setEpissglu(float epissglu) {
        this.epissglu = epissglu;
    }

    public String getEeyepre() {
        return eeyepre;
    }

    public void setEeyepre(String eeyepre) {
        this.eeyepre = eeyepre;
    }

    public String getEpanc() {
        return epanc;
    }

    public void setEpanc(String epanc) {
        this.epanc = epanc;
    }

    public float getEtc() {
        return etc;
    }

    public void setEtc(float etc) {
        this.etc = etc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.examinationid);
        dest.writeInt(this.userid);
        dest.writeLong(this.etime);
        dest.writeFloat(this.etcho);
        dest.writeString(this.epressure);
        dest.writeFloat(this.ebmi);
        dest.writeInt(this.edbp);
        dest.writeInt(this.esys);
        dest.writeFloat(this.eglu);
        dest.writeString(this.eliverc);
        dest.writeString(this.ekidneyc);
        dest.writeString(this.eglanc);
        dest.writeString(this.emilkc);
        dest.writeString(this.echest);
        dest.writeString(this.egutc);
        dest.writeInt(this.eph);
        dest.writeFloat(this.epissglu);
        dest.writeString(this.eeyepre);
        dest.writeString(this.epanc);
        dest.writeFloat(this.etc);
    }

    public ExaminationBean() {
    }

    protected ExaminationBean(Parcel in) {
        this.examinationid = in.readInt();
        this.userid = in.readInt();
        this.etime = in.readLong();
        this.etcho = in.readFloat();
        this.epressure = in.readString();
        this.ebmi = in.readFloat();
        this.edbp = in.readInt();
        this.esys = in.readInt();
        this.eglu = in.readFloat();
        this.eliverc = in.readString();
        this.ekidneyc = in.readString();
        this.eglanc = in.readString();
        this.emilkc = in.readString();
        this.echest = in.readString();
        this.egutc = in.readString();
        this.eph = in.readInt();
        this.epissglu = in.readFloat();
        this.eeyepre = in.readString();
        this.epanc = in.readString();
        this.etc = in.readFloat();
    }

    public static final Creator<ExaminationBean> CREATOR = new Creator<ExaminationBean>() {
        public ExaminationBean createFromParcel(Parcel source) {
            return new ExaminationBean(source);
        }

        public ExaminationBean[] newArray(int size) {
            return new ExaminationBean[size];
        }
    };
}
