package com.example.songyang.healthmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SongYang on 2016/4/11.
 */
public class HealthSQLiteOpenHelper extends SQLiteOpenHelper {
    public HealthSQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLS.CREATE_USER_TABLE);
        db.execSQL(SQLS.CREATE_CLINIC_TABLE);
        db.execSQL(SQLS.CREATE_HOSPITAL_TABLE);
        db.execSQL(SQLS.CREATE_EXAMINATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
