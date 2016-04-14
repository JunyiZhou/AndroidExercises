package com.example.songyang.healthmanager.db;

/**
 * Created by JunyiZhou on 2016/4/11.
 */
public class SQLS {
    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user(" +
            "userid INTEGER PRIMARY KEY, " +
            "username VARCHAR(10), " +
            "password VARCHAR(50), " +
            "sex VARCHAR(10), " +
            "age INTEGER, " +
            "nation VARCHAR(10), " +
            "phone VARCHAR(11), " +
            "idcard VARCHAR(18))";

    public static final String CREATE_CLINIC_TABLE = "CREATE TABLE IF NOT EXISTS clinic(" +
            "clinicid INTEGER PRIMARY KEY, " +
            "userid INTEGER NOT NULL, " +
            "consultation VARCHAR(1000), " +
            "cdocter VARCHAR(10), " +
            "ctime TIMESTAMP, " +
            "chistory VARCHAR(1000), " +
            "ctest VARCHAR(1000), " +
            "cresult VARCHAR(1000), " +
            "crecommend VARCHAR(1000), " +
            "ccholesterin FLOAT, " +
            "cgao FLOAT, " +
            "cdi FLOAT, " +
            "cserumo FLOAT, " +
            "cserumm FLOAT, " +
            "csugar FLOAT, " +
            "FOREIGN KEY (userid) REFERENCES user(userid))";

    public static final String CREATE_HOSPITAL_TABLE = "CREATE TABLE IF NOT EXISTS hospital(" +
            "hospitalid INTEGER PRIMARY KEY, " +
            "userid INTEGER, " +
            "htime TIMESTAMP, " +
            "hdianose VARCHAR(1000), " +
            "hday INTEGER, " +
            "hresult VARCHAR(1000), " +
            "FOREIGN KEY (userid) REFERENCES user(userid));";

    public static final String CREATE_EXAMINATION_TABLE = "CREATE TABLE IF NOT EXISTS examination(" +
            "examinationid INTEGER PRIMARY KEY, " +
            "userid INTEGER, " +
            "etime TIMESTAMP" +
            "etcho FLOAT, " +
            "epressure VARCHAR(10), " +
            "ebmi FLOAT, " +
            "edbp INTEGER, " +
            "esys INTEGER, " +
            "eglu FLOAT, " +
            "eliverc VARCHAR(1000), " +
            "ekidneyc VARCHAR(1000), " +
            "eglanc VARCHAR(1000), " +
            "emilkc VARCHAR(1000), " +
            "echest VARCHAR(1000), " +
            "egutc VARCHAR(1000), " +
            "eph INTEGER, " +
            "epissglu FLOAT, " +
            "eeyepre VARCHAR(1000), " +
            "epanc VARCHAR(1000), " +
            "etc FLOAT, " +
            "FOREIGN KEY (userid) REFERENCES user(userid));";
}
