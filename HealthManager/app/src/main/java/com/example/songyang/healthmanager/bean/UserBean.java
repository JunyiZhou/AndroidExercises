package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/12.
 */
@StorIOSQLiteType(table = "user")
public class UserBean {
    @StorIOSQLiteColumn(name = "userid", key = true)
    int userid;

    @StorIOSQLiteColumn(name = "username")
    String username;

    @StorIOSQLiteColumn(name = "password")
    String password;

    @StorIOSQLiteColumn(name = "sex")
    String sex;

    @StorIOSQLiteColumn(name = "age")
    int age;

    @StorIOSQLiteColumn(name = "nation")
    String nation;

    @StorIOSQLiteColumn(name = "phone")
    String phone;

    @StorIOSQLiteColumn(name = "idcard")
    String idcard;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
