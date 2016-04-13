package com.example.songyang.healthmanager.bean;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by JunyiZhou on 2016/4/12.
 */
@StorIOSQLiteType(table = "record")
public class RecordBean {
    public RecordBean() {
    }

    public RecordBean(int id, int userId, String consultation) {
        this.id = id;
        this.userId = userId;
        this.consultation = consultation;
    }

    @StorIOSQLiteColumn(name = "id", key = true)
    int id;

    @StorIOSQLiteColumn(name = "user_id")
    int userId;

    @StorIOSQLiteColumn(name = "consultation")
    String consultation;
}
