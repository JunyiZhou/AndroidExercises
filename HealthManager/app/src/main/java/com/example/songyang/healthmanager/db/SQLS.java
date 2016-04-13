package com.example.songyang.healthmanager.db;

/**
 * Created by JunyiZhou on 2016/4/11.
 */
public class SQLS {
    public static final String CREATE_USER_TABLE = "create table if not exists user(id integer primary key, name varchar, password varchar)";
    public static final String CREATE_RECORD_TABLE = "create table if not exists record(id integer primary key, user_id varchar, consultation varchar)";
}
