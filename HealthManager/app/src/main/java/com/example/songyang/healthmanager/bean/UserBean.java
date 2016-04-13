package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/12.
 */
@StorIOSQLiteType(table = "user")
public class UserBean {
    public UserBean() {
    }

    public UserBean(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @StorIOSQLiteColumn(name = "id", key = true)
    int id;

    @StorIOSQLiteColumn(name = "name")
    String name;

    @StorIOSQLiteColumn(name = "password")
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
