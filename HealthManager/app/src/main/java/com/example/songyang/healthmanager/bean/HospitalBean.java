package com.example.songyang.healthmanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by SongYang on 2016/4/14.
 */
@StorIOSQLiteType(table = "hospital")
public class HospitalBean implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.hospitalid);
        dest.writeInt(this.userid);
        dest.writeLong(this.htime);
        dest.writeString(this.hdianose);
        dest.writeInt(this.hday);
        dest.writeString(this.hresult);
    }

    public HospitalBean() {
    }

    protected HospitalBean(Parcel in) {
        this.hospitalid = in.readInt();
        this.userid = in.readInt();
        this.htime = in.readLong();
        this.hdianose = in.readString();
        this.hday = in.readInt();
        this.hresult = in.readString();
    }

    public static final Creator<HospitalBean> CREATOR = new Creator<HospitalBean>() {
        public HospitalBean createFromParcel(Parcel source) {
            return new HospitalBean(source);
        }

        public HospitalBean[] newArray(int size) {
            return new HospitalBean[size];
        }
    };
}
